package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.common.RoomComfort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomComfortRepository extends JpaRepository<RoomComfort, Long> {
    List<RoomComfort> findAllByRoomId(Long roomId);
}
