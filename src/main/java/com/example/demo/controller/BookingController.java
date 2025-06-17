package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.servise.BookingServise;
import com.example.demo.servise.CustomerServise;
import com.example.demo.servise.RoomServise;
import com.example.demo.exception.*;

@Controller
public class BookingController {
	@Autowired
	BookingServise  bookingServise;
	
	@Autowired
	CustomerServise customerServise;
	@Autowired
	RoomServise roomServise;
	
	
	
	@GetMapping("/booking/viewall")
	public ResponseEntity<List<Booking>>listallBookings(){
		List<Booking>bookings = bookingServise.listall();
		return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);}
	

	@GetMapping("/booking/getbybookid/{id}")
	public ResponseEntity<Booking> getbybookid(@PathVariable Long id) {
		Booking booking = bookingServise.findbyid(id);
		return new ResponseEntity<Booking>(booking,HttpStatus.OK);
	}
	@GetMapping("/booking/getbycustomerid")
	public ResponseEntity<List<Booking> >getbycustomerid(@PathVariable Long id) {
		List<Booking> booking = bookingServise.findbycustomerid(id);
		return new ResponseEntity<List<Booking>>(booking,HttpStatus.OK);
	}
	@DeleteMapping("/booking/delete/{id}")
	public ResponseEntity<Void>deletebybookinglid(@PathVariable Long id){
		Booking deletebooking = bookingServise.delete(id);
		if(deletebooking==null)return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/Booking/add/{roomId}/{customerId}")
	public ResponseEntity<Booking> addhotel(@RequestBody 	Booking booking,@PathVariable("roomId") Long roomID,@PathVariable("customerId") Long customerID ){
		Customer customer =customerServise.findbyid(customerID);
		Room room = roomServise.findbyid(roomID).orElseThrow(()-> new ResourceNotFoundException("roomid not found"+roomID));
		booking.setCustomer(customer);
		booking.setRoom(room);
		Booking bookingadd=bookingServise.addit(booking);
		return new ResponseEntity<Booking>(bookingadd,HttpStatus.OK);
	}
	
	
}
