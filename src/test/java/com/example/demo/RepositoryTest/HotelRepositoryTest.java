package com.example.demo.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.repository.HotelRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class HotelRepositoryTest {
	@Autowired
	HotelRepository hotelRepository;
	
	@Test
	public void saveAndFindbyname() {

		List<Room> rooms = Arrays.asList(
			    new Room(null, "101", "single", new BigDecimal("300.0"), null, null),
			    new Room(null, "102", "double", new BigDecimal("600.0"), null, null)
			);
		Hotel newhotel = new Hotel(null, "mugil", "chennai",rooms );
		for (Room room : rooms) {
		    room.setHotel(newhotel);
		}
		hotelRepository.save(newhotel);
		
		Optional<Hotel>foundHotel = hotelRepository.findById(newhotel.getHostelID());
		assertThat(foundHotel).isPresent();
		assertThat(foundHotel.get().getName()).isEqualTo("mugil");
	}
	
	@Test
	public void saveandfindbyLocation() {
//		
//		List<Room> rooms = Arrays.asList(
//			    new Room(null, "101", "single", new BigDecimal("300.0"), null, null),
//			    new Room(null, "102", "double", new BigDecimal("600.0"), null, null)
//			);
		Hotel newhotel = new Hotel(null, "jash", "vellore",null );
		hotelRepository.save(newhotel);
		
		List<Hotel>foundHotel = hotelRepository.findByLocation(newhotel.getLocation());
		assertThat(foundHotel).hasSize(1);
		assertThat(foundHotel.get(0).getLocation()).isEqualTo("vellore");
		
	}
	@Test
	
	public void saveandverifybyCount() {
		Hotel newhotel = new Hotel(null, "tash", "banglore",null );
		hotelRepository.save(newhotel);
		Hotel newhotel2 = new Hotel(null, "bosh", "mad",null );
		hotelRepository.save(newhotel2);
		Hotel newhotel3 = new Hotel(null, "gash", "gurkan",null );
		hotelRepository.save(newhotel3);
		long count = hotelRepository.count();
		assertThat(count).isEqualTo(3);
		
	}
	@Test
	public void saveDeletandarssert() {
		Hotel newhotel = new Hotel(null, "tash", "banglore",null );
		hotelRepository.save(newhotel);
		
		assertThat(hotelRepository.count()).isEqualTo(1);
		hotelRepository.delete(newhotel);
		assertThat(hotelRepository.count()).isEqualTo(0);
		assertThat(hotelRepository.findById(newhotel.getHostelID())).isNotPresent();
		
	}
	@Test
	public void saveandlistall() {
		Hotel newhotel = new Hotel(null, "tash", "banglore",null );
		hotelRepository.save(newhotel);
		Hotel newhotel2 = new Hotel(null, "bosh", "mad",null );
		hotelRepository.save(newhotel2);
		Hotel newhotel3 = new Hotel(null, "gash", "gurkan",null );
		hotelRepository.save(newhotel3);
		int count = (int) hotelRepository.count();
		List<Hotel>hotels = hotelRepository.findAll();
		assertThat(hotels).isNotNull();
		assertThat(hotels.size()).isEqualTo(count);	
		assertThat(hotels).extracting("name").contains("tash","bosh","gash");
	}
	
	
	
	

}
