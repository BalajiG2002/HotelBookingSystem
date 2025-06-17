package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.repository.HotelRepository;

@Service
public class HotelServise {
	@Autowired
	HotelRepository hotelRepository;
	
	
	public Hotel addit(Hotel hotel) {
		return hotelRepository.save(hotel);
		
	}
	
	public List<Hotel> listall() {
		 return hotelRepository.findAll();
		
	}
	
	public Hotel delete(Long id) {
		hotelRepository.deleteById(id);
		return null;
		
	}
	public Hotel findbyid(Long id) {
		return hotelRepository.findById(id).orElse(null);
		
	}
	
	public Hotel update(Long id,Hotel newhotel) {
		
		Hotel exsistingHotel =   hotelRepository.findById(id).orElse(null);
		exsistingHotel.setLocation(newhotel.getLocation());
		exsistingHotel.setName(newhotel.getName());
		
		List<Room>existingrooms = exsistingHotel.getRooms();
		existingrooms.clear();
		if(newhotel.getRooms()!= null) {
			for(Room room:newhotel.getRooms()) {
				room.setHotel(exsistingHotel);
			}
		}
		return hotelRepository.save(exsistingHotel);
		
	}
		
		
	}
