package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.common.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomePageItemsRepository extends JpaRepository<RoomCategory, Long> {
    List<RoomCategory> findAllByCategory_Id(Long categoryId);

}
