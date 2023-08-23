package com.masai.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@ManyToOne
	@JoinColumn(name = "home_id", nullable = false)
	private Home home;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private boolean isCancelled;

	
	
	// Default Constructor

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	// Parameterized constructor 
	
	public Booking(Home home, Customer customer, LocalDate startDate, LocalDate endDate, boolean isCancelled
			) {
		super();
		this.home = home;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isCancelled = isCancelled;
		
	}

// All getter and setter
	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public Home getHome() {
		return home;
	}


	public void setHome(Home home) {
		this.home = home;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public boolean isCancelled() {
		return isCancelled;
	}


	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}


	
	
	
	
	
	
	
	
	
	
	
	


}
