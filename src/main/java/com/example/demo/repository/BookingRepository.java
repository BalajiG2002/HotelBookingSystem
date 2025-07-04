package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;

import java.util.List;
import java.time.LocalDate;



public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByCustomer(Customer customer);
	List<Booking> findByCheckInDate(LocalDate checkInDate);

}
