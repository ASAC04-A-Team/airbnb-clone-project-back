package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomItemsRepository extends JpaRepository<Room, Long> {
    List<Room> findAll();

}
