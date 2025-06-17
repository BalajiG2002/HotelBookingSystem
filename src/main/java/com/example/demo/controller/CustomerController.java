package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Customer;
import com.example.demo.model.Hotel;
import com.example.demo.servise.CustomerServise;
@Controller
public class CustomerController {
	
	@Autowired
	CustomerServise customerServise;
	
	@GetMapping("/customer/viewall")
	public ResponseEntity<List<Customer>>listallCustomers(){
		List<Customer>customers = customerServise.listall();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
		
	}

	@PostMapping("/customer/add")
	public ResponseEntity<Customer> addhotel(@RequestBody 	Customer customer){
	 Customer customeradd=customerServise.addit(customer);
		return new ResponseEntity<Customer>(customeradd,HttpStatus.OK);
	}

	
	@GetMapping("/customer/getbycustomerid/{id}")
	public ResponseEntity<Customer> getbyhostelid(@PathVariable Long id) {
		Customer Customer = customerServise.findbyid(id);
		return new ResponseEntity<Customer>(Customer,HttpStatus.OK);
	}
	

}
