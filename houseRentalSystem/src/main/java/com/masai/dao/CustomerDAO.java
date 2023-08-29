package com.masai.dao;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface CustomerDAO {
	Customer addCustomer(Customer customer) throws SomethingWentWrongException;

	Customer updateCustomer(Customer customer) throws SomethingWentWrongException;

	void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Customer> getAllCustomers() throws SomethingWentWrongException;

	Customer getCustomerById(Long customerId) throws NoRecordFoundException;

	Customer getCustomerByUsername(String username) throws NoRecordFoundException;

	void login(String username, String password) throws SomethingWentWrongException;

}
