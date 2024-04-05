package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.common.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
    Optional<List<RoomCategory>> findAllByCategoryId(Long categoryId);
}
