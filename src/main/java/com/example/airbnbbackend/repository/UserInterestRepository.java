package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.domain.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest,Long> {

    UserInterest findUserInterestByUserId(Long UserId);

}
