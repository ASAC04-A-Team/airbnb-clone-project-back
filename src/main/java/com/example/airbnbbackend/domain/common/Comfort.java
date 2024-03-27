package com.example.airbnbbackend.domain.common;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comfort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comfort_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_comfort_id")
    private RoomComfort roomComfort;
}
