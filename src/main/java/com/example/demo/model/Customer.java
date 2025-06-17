package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;
    private String name;
    private String email;
    private String password; // This will be encoded

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Booking> bookings;

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Customer(Long customerID, String name, String email, String password, List<Booking> bookings) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.bookings = bookings;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", bookings=" + bookings.size() + "]";
	}
    
}
