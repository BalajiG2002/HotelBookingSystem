package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomID;
    private String roomNumber;
    private String type;  // e.g., Single, Double, Suite
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hostelID")
    @JsonIgnore
    private Hotel hotel;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Booking> bookings;

	public Long getRoomID() {
		return roomID;
	}

	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Room(Long roomID, String roomNumber, String type, BigDecimal price, Hotel hotel, List<Booking> bookings) {
		super();
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.type = type;
		this.price = price;
		this.hotel = hotel;
		this.bookings = bookings;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomNumber=" + roomNumber + ", type=" + type + ", price=" + price
				+ ", hotel=" + hotel + ", bookings=" + bookings.size() + "]";
	}
    
    
    
}
