package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Room;
import com.example.demo.repository.CustomerRepository;
@Service
public class CustomerServise {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RoomServise roomServise;
	public Customer addit(Customer customer) {
	    return customerRepository.save(customer);
	}

	public List<Customer> listall() {
		 return customerRepository.findAll();
		
	}
	
	public Booking delete(Long id) {
		customerRepository.deleteById(id);
		return null;
		
	}
	public Customer findbyid(Long id) {
		return customerRepository.findById(id).orElse(null);
		
	}
	

}
