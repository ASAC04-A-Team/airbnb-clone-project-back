package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.HostReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserHostReviewsRepository extends JpaRepository<HostReview,Long> {
    List<HostReview> findHostReviewsByUserId(Long userId);
}
