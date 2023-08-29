package com.masai.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.masai.dao.CustomerDAO;
import com.masai.dao.CustomerDAOImpl;
import com.masai.entity.Customer;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer addCustomer(Customer customer) throws SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.addCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		cd.deleteCustomer(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() throws SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(Long customerId) throws NoRecordFoundException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.getCustomerById(customerId);
	}

	@Override
	public Customer getCustomerByUsername(String username) throws NoRecordFoundException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.getCustomerByUsername(username);
	}

	@Override
	public void login(String username, String password) throws SomethingWentWrongException{
		CustomerDAO cd = new CustomerDAOImpl();
		cd.login(username, password);

	}

}
