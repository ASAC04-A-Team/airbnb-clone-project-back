package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.common.RoomAdvantage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAdvantageRepository extends JpaRepository<RoomAdvantage, Long> {
    List<RoomAdvantage> findAllByRoomId(Long roomId);
}
