package com.example.demo.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.example.demo.model.*;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
public class RoomRepositoryTest {
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	HotelRepository hotelRepository;

	@Test
	public void saveAndTestbyid() {
		Hotel hotel = new Hotel(null, "VISHNUVILLAS", "gudiyatham", null);
		hotelRepository.save(hotel);
		Room newroom = new Room(null, "121", "single", new BigDecimal(200), hotel, null);
		roomRepository.save(newroom);
		
		Optional<Room>foundroom = roomRepository.findById(newroom.getRoomID());
		assertThat(foundroom.get().getRoomID()).isEqualByComparingTo(newroom.getRoomID());
	}
	
	@Test
	public void saveAndTestbyname() {
		Hotel hotel = new Hotel(null, "VISHNUVILLAS", "gudiyatham", null);
		hotelRepository.save(hotel);
		Room newroom = new Room(null, "121", "single", new BigDecimal(200), hotel, null);
		roomRepository.save(newroom);
		
		Optional<Room>foundroom = roomRepository.findByRoomNumber(newroom.getRoomNumber());
		assertThat(foundroom.get().getRoomNumber()).isEqualTo(newroom.getRoomNumber());
	}
	@Test
	public void SaveAndTestlistall() {
		Hotel hotel = new Hotel(null, "VISHNUVILLAS", "gudiyatham", null);
		hotelRepository.save(hotel);
		Room newroom = new Room(null, "121", "single", new BigDecimal(200), hotel, null);
		roomRepository.save(newroom);
		Room newroom1 = new Room(null, "121", "single", new BigDecimal(200), hotel, null);
		roomRepository.save(newroom1);
		Room newroom2 = new Room(null, "121", "single", new BigDecimal(200), hotel, null);
		roomRepository.save(newroom2);
		
		List<Room>rooms = roomRepository.findAll();
		int count = (int) roomRepository.count();
		
		assertThat(rooms.size()).isEqualTo(count);
		
	}
	@Test
	public void Deletetest() {
		Hotel hotel = new Hotel(null, "VISHNUVILLAS", "gudiyatham", null);
		hotelRepository.save(hotel);
		Room newroom = new Room(null, "121", "single", new BigDecimal(200), hotel, null);
		roomRepository.save(newroom);
		roomRepository.delete(newroom);
		assertThat(roomRepository.count()).isEqualTo(0);
		
	}
	
	
	
}
