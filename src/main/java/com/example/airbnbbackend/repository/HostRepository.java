package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    Host findHostByRoomId(Long roomId);
}
