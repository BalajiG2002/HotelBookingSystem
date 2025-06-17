package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Room;
import com.example.demo.repository.BookingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServise {

	
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	CustomerServise customerServise;
	@Autowired
	RoomServise roomServise;
	
	public Booking addit(Booking booking) {
	
		return bookingRepository.save(booking);
		
	}
	
	public List<Booking> listall() {
		 return bookingRepository.findAll();
		
	}
	
	public Booking delete(Long id) {
		bookingRepository.deleteById(id);
		return null;
		
	}
	public Booking findbyid(Long id) {
		return bookingRepository.findById(id).orElse(null);
		
	}
	
	public List<Booking> findbycustomerid(Long id) {
		Customer customer =  customerServise.findbyid(id);
		return bookingRepository.findByCustomer(customer);
		
	}
	
	
	
	
	
	
	
}
