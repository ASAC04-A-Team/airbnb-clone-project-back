package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest,Long> {
}
