package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.domain.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest,Long> {

    //Optional<UserInterest> findUserInterestByUserId(Long UserId);
    Optional<UserInterest> findUserInterestByUserId(Long UserId);

}
