package com.masai.service;

import java.util.List;

import com.masai.entity.Payment;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface PaymentService {
	Payment makePayment(Payment payment) throws SomethingWentWrongException;

	void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Payment> getAllPayments() throws SomethingWentWrongException;

	Payment getPaymentById(Long paymentId) throws NoRecordFoundException;

	List<Payment> getPaymentsByBooking(Long customerId) throws SomethingWentWrongException;

}
