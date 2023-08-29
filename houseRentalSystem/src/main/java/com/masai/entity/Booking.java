package com.masai.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
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
	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
	private Payment payment;

	
	
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


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public void printBookingsDetails() {
		System.out.println("---------------------------------------------------------");
		System.out.println("Booking ID: " + this.getBookingId());
		System.out.println("Home ID: " + this.getHome().getHomeId());
		System.out.println("Customer ID: " + this.getCustomer().getCustomerId());
		System.out.println("Start Date: " + this.getStartDate());
		System.out.println("End Date: " + this.getEndDate());
		System.out.println("Is Cancelled: " + this.isCancelled());
		if (this.getPayment() != null) {
			System.out.println("Payment ID: " + this.getPayment().getPaymentId());
		} else {
			System.out.println("Payment: Pending");
		}
		System.out.println("---------------------------------------------------------");
	}
}
