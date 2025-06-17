package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostelID;
	private String name;
    private String location;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;

	public Long getHostelID() {
		return hostelID;
	}

	public void setHostelID(Long hostelID) {
		this.hostelID = hostelID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Hotel(Long hostelID, String name, String location, List<Room> rooms) {
		super();
		this.hostelID = hostelID;
		this.name = name;
		this.location = location;
		this.rooms = rooms;
	}

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Hotel [hostelID=" + hostelID + ", name=" + name + ", location=" + location + ", rooms=" + rooms.size() + "]";
	}
	
	
}
