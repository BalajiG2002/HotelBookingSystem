package com.example.demo.controller;
import com.example.demo.exception.*;
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

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.servise.HotelServise;
import com.example.demo.servise.RoomServise;

@Controller
public class RoomController {
	@Autowired 
	RoomServise roomServise;
	@Autowired
	HotelServise hotelServise;
	
	
	@DeleteMapping("/room/delete/{id}")
	public ResponseEntity<Void>deletebyroomlid(@PathVariable Long id){
		Room deleteroom = roomServise.delete(id);
		if(deleteroom==null)return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/rooms/getbyid/{id}")
	public ResponseEntity<Room> getbyid(@PathVariable Long id) {
		Room room = roomServise.findbyid(id).orElseThrow(()->new ResourceNotFoundException("room id not found"+id));
		return new ResponseEntity<Room>(room,HttpStatus.OK);
	}
	
	@GetMapping("/room/viewall")
	public ResponseEntity<List<Room>>listallrooms(){
		List<Room>rooms = roomServise.listall();
		return new ResponseEntity<List<Room>>(rooms,HttpStatus.OK);
		
	}
	@PostMapping("/room/add/{hotelId}")
	public ResponseEntity<Room> addhotel(@RequestBody Room room	,@PathVariable("hotelId") Long hotelId ){
		Hotel hotel =hotelServise.findbyid(hotelId);
		room.setHotel(hotel);
		Room addroom = roomServise.addit(room);
		return new ResponseEntity<Room>(addroom,HttpStatus.OK);
	}
	@GetMapping("/room/{hotelId}")
	public ResponseEntity<Room>getbyhotelid(@PathVariable("hotelId") Long hotelId ){
		Room rooms = roomServise.findbyid(hotelId).orElse(null);
		return new ResponseEntity<Room>(rooms, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	

}
