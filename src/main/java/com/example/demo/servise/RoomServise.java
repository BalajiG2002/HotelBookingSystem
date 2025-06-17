package com.example.demo.servise;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;

@Service
public class RoomServise {
	@Autowired
	RoomRepository roomRepository;
	
	
	public Room addit(Room room) {
		return roomRepository.save(room);
		
	}
	
	public List<Room> listall() {
		 return roomRepository.findAll();
		
	}
	
	public Room delete(Long id) {
		roomRepository.deleteById(id);
		return null;
		
	}
	public Optional<Room>  findbyid(Long id) {
		return roomRepository.findById(id);		
	}
	
	
	
	
	

}
