package com.masai.service;

import java.util.List;

import com.masai.dao.PaymentDAO;
import com.masai.dao.PaymentDAOImpl;
import com.masai.entity.Payment;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public class PaymentServiceImpl implements PaymentService{

	@Override
	public Payment makePayment(Payment payment) throws SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.makePayment(payment);
	}

	@Override
	public void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		pd.cancelPayment(paymentId);;
		
	}

	@Override
	public List<Payment> getAllPayments() throws SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.getAllPayments();
	}

	@Override
	public Payment getPaymentById(Long paymentId) throws NoRecordFoundException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.getPaymentById(paymentId);
	}

	@Override
	public List<Payment> getPaymentsByBooking(Long customerId) throws SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.getPaymentsByReservation(customerId);
	}

}
