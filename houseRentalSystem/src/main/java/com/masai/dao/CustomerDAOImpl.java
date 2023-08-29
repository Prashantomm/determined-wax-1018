package com.masai.dao;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.masai.Utility.Util;
import com.masai.entity.Customer;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CustomerDAOImpl implements CustomerDAO {

	
	@Override
	public Customer addCustomer(Customer customer) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			em.persist(customer);
			et.commit();
			return customer;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	
	@Override
	public Customer updateCustomer(Customer customer) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			et.begin();
			Customer c1 = em.find(Customer.class, customer.getCustomerId());
			if (c1 != null) {
				em.merge(customer);
				et.commit();
				return customer;
			} else {
				throw new NoRecordFoundException("No customer found with the given ID :" + customer.getCustomerId());
			}

		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while Updating details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Customer customer = em.find(Customer.class, customerId);
			et.begin();
			if (customer != null) {

				customer.setDeleted(true);
				em.merge(customer);
				et.commit();
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + customerId);
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}

	}

	
	@Override
	public List<Customer> getAllCustomers() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Query query = em.createQuery("SELECT c FROM Customer c");
			et.begin();
			List<Customer> list = (List<Customer>) query.getResultList();
			if (list != null) {
				et.commit();
				return list;
			} else {
				throw new SomethingWentWrongException("List is empty.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	
	@Override
	public Customer getCustomerById(Long customerId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Customer customer = em.find(Customer.class, customerId);
			et.begin();
			if (customer != null) {
				et.commit();
				return customer;
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + customerId);
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	
	@Override
	public Customer getCustomerByUsername(String username) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			String jpql = "SELECT c FROM Customer c WHERE c.username = :username";
			Query query = em.createQuery(jpql);
			query.setParameter("username", username);

			Customer customer = (Customer) query.getSingleResult();

			et.begin();
			if (customer != null) {
				et.commit();
				return customer;
			} else {
				throw new NoRecordFoundException("No record found with the given username: " + username);
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while Getting Details. Try again later.");
		} finally {
			em.close();
		}
	}

	
	@Override
	public void login(String username, String password) throws SomethingWentWrongException {
		EntityManager em = null;

		try {
			em = Util.getEm();

			// Retrieve the customer by username from the database
			Query query = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username");
			query.setParameter("username", username);
			Customer customer = (Customer) query.getSingleResult();

			// Check if the provided password matches the customer's password
			if (customer != null && customer.getPassword().equals(password)) {
				// Perform any additional login-related actions here
				System.out.println("Login successful!");
			} else {
				// Invalid credentials
				System.out.println("Invalid username or password. Please try again.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			em.close();
		}
	}
}