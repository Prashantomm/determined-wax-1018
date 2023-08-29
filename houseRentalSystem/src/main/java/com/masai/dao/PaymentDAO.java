package com.masai.dao;

import java.util.List;

import com.masai.entity.Payment;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface PaymentDAO {
	Payment makePayment(Payment payment) throws SomethingWentWrongException;

	void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Payment> getAllPayments() throws SomethingWentWrongException;

	Payment getPaymentById(Long paymentId) throws NoRecordFoundException;

	List<Payment> getPaymentsByReservation(Long customerId) throws SomethingWentWrongException;

}
