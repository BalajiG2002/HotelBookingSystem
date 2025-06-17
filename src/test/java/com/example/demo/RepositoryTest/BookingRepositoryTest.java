package com.example.demo.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Booking;
import com.example.demo.model.Room;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.RoomRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class BookingRepositoryTest {
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	RoomRepository roomRepository;
	@Test
	public void saveAndtestbyid() {
		Room room = new Room(null, "101", "double", new BigDecimal (300), null, null);
		roomRepository.save(room);
		Booking newbooking = new Booking(null, LocalDate.parse("2020-09-09"), LocalDate.parse("2020-09-08"), room, null);
		bookingRepository.save(newbooking);
		
		Optional<Booking>foundBooking = bookingRepository.findById(newbooking.getId());
		assertThat(foundBooking.get().getId()).isEqualTo(newbooking.getId());
		
	}
	
	@Test
	public void saveAndtestbycheckindate() {
		Room room = new Room(null, "101", "double", new BigDecimal (300), null, null);
		roomRepository.save(room);
		Booking newbooking = new Booking(null, LocalDate.parse("2020-09-09"), LocalDate.parse("2020-09-08"), room, null);
		bookingRepository.save(newbooking);
		
		List<Booking>foundBooking = bookingRepository.findByCheckInDate( LocalDate.parse("2020-09-09"));
		assertThat(foundBooking.get(0).getCheckInDate()).isEqualTo(newbooking.getCheckInDate());	
	}
	@Test
	public void saveAndtestBYlistall() {
		Room room = new Room(null, "101", "double", new BigDecimal (300), null, null);
		roomRepository.save(room);

		Booking newbooking = new Booking(null, LocalDate.parse("2020-09-09"), LocalDate.parse("2020-09-08"), room, null);
		bookingRepository.save(newbooking);
		
		Booking newbooking1 = new Booking(null, LocalDate.parse("2020-09-09"), LocalDate.parse("2020-09-08"), room, null);
		bookingRepository.save(newbooking1);
		Booking newbooking2 = new Booking(null, LocalDate.parse("2020-09-09"), LocalDate.parse("2020-09-08"), room, null);
		bookingRepository.save(newbooking2); 
		int count=(int) bookingRepository.count();
		
		
		List<Booking>foundBooking = bookingRepository.findAll();
		assertThat(foundBooking).size().isEqualByComparingTo(count);
	}
	@Test
	public void saveAnddelete() {
		Room room = new Room(null, "101", "double", new BigDecimal (300), null, null);
		roomRepository.save(room);

		Booking newbooking = new Booking(null, LocalDate.parse("2020-09-09"), LocalDate.parse("2020-09-08"), room, null);
		bookingRepository.save(newbooking);
		
		assertThat(bookingRepository.count()).isEqualTo(1);
		bookingRepository.delete(newbooking);
		assertThat(bookingRepository.count()).isEqualTo(0);
		
	}
	
	
	

}
