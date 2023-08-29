package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.dao.BookingDAO;
import com.masai.dao.BookingDAOImpl;
import com.masai.entity.Booking;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public class BookingServiceImpl implements BookingService {

	@Override
	public Booking makeBooking(Booking booking) throws SomethingWentWrongException {
		BookingDAO bd = new BookingDAOImpl();
		return bd.makeBooking(booking);
	}

	@Override
	public void cancelBooking(Long bookingId) throws NoRecordFoundException, SomethingWentWrongException {
		BookingDAO bd = new BookingDAOImpl();
		 bd.cancelBooking(bookingId);

	}

	@Override
	public List<Booking> getAllBookings() throws SomethingWentWrongException {
		BookingDAO bd = new BookingDAOImpl();
		return bd.getAllBookings();
	}

	@Override
	public Booking getBookingById(Long bookingId) throws NoRecordFoundException {
		BookingDAO bd = new BookingDAOImpl();
		return bd.getBookingById(bookingId);
	}

	@Override
	public List<Booking> getBookingsByCustomer(Long customerId) throws NoRecordFoundException {
		BookingDAO bd = new BookingDAOImpl();
		return bd.getBookingByCustomer(customerId);
	}

	@Override
	public List<Booking> getBookingsByHome(Long homeId) throws NoRecordFoundException {
		BookingDAO bd = new BookingDAOImpl();
		return bd.getBookingsByHome(homeId);
	}

	@Override
	public List<Booking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException {
		BookingDAO bd = new BookingDAOImpl();
		return bd.getBookingsBetweenDates(startDate, endDate);
	}

}
