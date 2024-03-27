package com.example.airbnbbackend.domain.common;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Advantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advantage_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_advantage_id")
    private RoomAdvantage roomAdvantage;
}
