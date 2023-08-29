package com.masai.dao;

import java.time.LocalDate;
import java.util.List;

import com.masai.Utility.Util;
import com.masai.entity.Booking;
import com.masai.entity.Home;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class BookingDAOImpl implements BookingDAO {

	@Override
	public Booking makeBooking(Booking booking) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			et.begin();

			// Step 1: Check if the car is available for the reservation
			Long homeId = booking.getHome().getHomeId();
			Home home = em.find(Home.class, homeId);
			if (home == null) {
				throw new NoRecordFoundException("No home found with the given ID: " + homeId);
			}

			

			// Step 2: Update car's availability to false
			home.setAvailability(false);

			em.merge(home);
			booking.setPayment(null);

			// Step 3: Persist the reservation in the database
			em.persist(booking);

			et.commit();
			return booking;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException(
					"Something went wrong while making the booking. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public void cancelBooking(Long bookingId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			et.begin();

			// Find the booking in the database
			Booking canceledBooking = em.find(Booking.class, bookingId);

			if (canceledBooking != null) {
				// Mark the booking as cancelled
				canceledBooking.setCancelled(true);

				// Retrieve the home associated with the booking
				Home home = canceledBooking.getHome();

				// Update the home;s availability to true
				home.setAvailability(true);

				// Save the changes to the booking and home
				em.merge(canceledBooking);
				em.merge(home);

				// Commit the transaction
				et.commit();

			} else {
				throw new NoRecordFoundException("No booking found with the given ID: " + bookingId);
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException(
					"Something went wrong while cancelling the booking. Try again later.");
		} finally {
			em.close();
		}

	}

	@Override
	public List<Booking> getAllBookings() throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT b FROM Booking b";
			Query query = em.createQuery(jpql);
			List<Booking> bookings = query.getResultList();
			return bookings;
		} catch (Exception e) {
			throw new SomethingWentWrongException(
					"Something went wrong while retrieving all bookings. Try again later.");
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Booking getBookingById(Long bookingId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Booking book = em.find(Booking.class, bookingId);
			et.begin();
			if (book != null) {
				et.commit();
				return book;
			} else {
				throw new NoRecordFoundException("Invalid Booking ID.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public List<Booking> getBookingByCustomer(Long customerId) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT b FROM Booking b WHERE b.customer.customerId = :customerId";
			Query query = em.createQuery(jpql);
			query.setParameter("customerId", customerId);
			List<Booking> bookings = query.getResultList();
			if (bookings.isEmpty()) {
				throw new NoRecordFoundException("No bookings found for customer with ID: " + customerId);
			}
			return bookings;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Booking> getBookingsByHome(Long homeId) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT b FROM Booking b WHERE b.home.homeId = :homeId";
			Query query = em.createQuery(jpql);
			query.setParameter("homeId", homeId);
			List<Booking> bookings = query.getResultList();
			if (bookings.isEmpty()) {
				throw new NoRecordFoundException("No bookings found for car with ID: " + homeId);
			}
			return bookings;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Booking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT b FROM Booking b WHERE b.startDate >= :startDate AND b.endDate <= :endDate";
			Query query = em.createQuery(jpql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			List<Booking> bookings = query.getResultList();
			return bookings;
		} catch (Exception e) {
			throw new SomethingWentWrongException(
					"Something went wrong while retrieving bookings between dates. Try again later.");
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
