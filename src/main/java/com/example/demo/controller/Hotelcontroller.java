package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.servise.BookingServise;
import com.example.demo.servise.CustomerServise;
import com.example.demo.servise.HotelServise;
import com.example.demo.servise.RoomServise;

@Controller
public class Hotelcontroller {
	@Autowired
	BookingServise bookingServise;
	@Autowired
	CustomerServise customerServise;
	@Autowired
	HotelServise hotelServise;
	@Autowired
	RoomServise roomServise;
	
	
	@GetMapping("/hotels/viewall")
	public ResponseEntity<List<Hotel>>listall(){
		List<Hotel>hotels = hotelServise.listall();
		return new ResponseEntity<List<Hotel>>(hotels,HttpStatus.OK);
		
	}
	
	@PostMapping("/hostel/add")
	public ResponseEntity<Hotel> addhotel(@RequestBody 	Hotel hotel){
	  Hotel hoteladd=hotelServise.addit(hotel);
		return new ResponseEntity<Hotel>(hoteladd,HttpStatus.OK);
	}
		@GetMapping("/hostel/{hotelId}/rooms")
	public ResponseEntity<List<Room>>listallroomsbyhotel(@PathVariable("hotelId") Long id){
		Hotel hotel = hotelServise.findbyid(id);
		List<Room>rooms = hotel.getRooms();
		return new ResponseEntity<List<Room>>(rooms,HttpStatus.OK);
		
	}
	@GetMapping("/hotel/getbyhostelid/{id}")
	public ResponseEntity<Hotel> getbyhostelid(@PathVariable Long id) {
		Hotel hotel = hotelServise.findbyid(id);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	@DeleteMapping("/hotel/delete/{id}")
	public ResponseEntity<Void>deletebyhotelid(@PathVariable Long id){
		Hotel deletehotel = hotelServise.delete(id);
		if(deletehotel==null)return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	@PutMapping("/hotel/update/{id}")
	public ResponseEntity<Hotel>updatehotel(@PathVariable Long id, @RequestBody Hotel hotel){
		Hotel updatehotel = hotelServise.update(id, hotel);
		return new ResponseEntity<Hotel>(updatehotel,HttpStatus.OK);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	

