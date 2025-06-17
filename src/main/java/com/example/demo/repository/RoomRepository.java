package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import java.util.List;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long>{
	Optional<Room> findByRoomNumber(String roomNumber);
	List<Room> findByHotel(Hotel hotel);

}
