package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.common.RoomComfort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomComfortRepository extends JpaRepository<RoomComfort, Long> {
    Optional<List<RoomComfort>> findAllByRoomId(Long roomId);
}
