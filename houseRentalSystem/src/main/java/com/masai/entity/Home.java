package com.masai.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Home {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long homeId;

	@Column(nullable = false)
	private String area;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private boolean availability;

	@Column(nullable = false)
	private boolean isDeleted;

	@OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
	private Set<Booking> bookings = new HashSet<>();

	

	public Home() { //Default Constructor
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//parameterized constructor

	public Home(String area, String type, double price, boolean availability, boolean isDeleted, Set<Booking> bookings
			) {
		super();
		this.area = area;
		this.type = type;
		this.price = price;
		this.availability = availability;
		this.isDeleted = isDeleted;
		this.bookings = bookings;
		
	}
	
	//All getter setter

	public Long getHomeId() {
		return homeId;
	}

	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	

}
