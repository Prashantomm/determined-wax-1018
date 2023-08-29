package com.masai.dao;

import java.time.LocalDate;
import java.util.List;

import com.masai.entity.Booking;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface BookingDAO {
	Booking makeBooking(Booking booking) throws SomethingWentWrongException;

    void cancelBooking(Long bookingId) throws NoRecordFoundException, SomethingWentWrongException;

    List<Booking> getAllBookings() throws SomethingWentWrongException;

    Booking getBookingById(Long bookingId) throws NoRecordFoundException;

	List<Booking> getBookingByCustomer(Long customerId) throws NoRecordFoundException;

	List<Booking> getBookingsByHome(Long homeId) throws NoRecordFoundException;

	List<Booking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException;
}
