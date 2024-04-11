package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long Id);

    boolean existsUserById(Long userId);

    User findByEmail(String email);

    Optional<User> findUserByEmail(String email);

    User getUserByEmail(String email);
}
