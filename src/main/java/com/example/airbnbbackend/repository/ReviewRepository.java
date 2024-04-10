package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<List<Review>> findReviewByRoomId(Long roomId);


    //jpql
    // 위치기반 파라미터 바인딩
    @Query(value = "SELECT m FROM Review m WHERE m.room.id = :room_id AND m.content LIKE %:content% ", nativeQuery = false)
    Optional<List<Review>> findReviewSearch(@Param("room_id") Long roomId, @Param("content") Optional<String> content);

}
