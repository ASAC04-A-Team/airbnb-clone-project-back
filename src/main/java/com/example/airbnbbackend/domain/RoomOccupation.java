package com.example.airbnbbackend.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "room_occupation")
public class RoomOccupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_occupation_id")
    private Long id;

    @Column(nullable = false)
    private Boolean isOccupied;

    @Column(nullable = false)
    private LocalDateTime checkInAt;

    @Column(nullable = false)
    private LocalDateTime checkOutAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}