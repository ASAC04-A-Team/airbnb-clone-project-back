package com.example.airbnbbackend.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_id")
    private Long id;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private LocalDateTime registerAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
