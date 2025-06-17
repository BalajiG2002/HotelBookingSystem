package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Hotel;
import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Long>{
List<Hotel> findByLocation(String location);

	
}
