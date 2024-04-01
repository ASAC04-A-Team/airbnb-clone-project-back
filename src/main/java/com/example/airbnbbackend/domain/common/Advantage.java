package com.example.airbnbbackend.domain.common;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "advantage", cascade = CascadeType.ALL)
    private List<RoomAdvantage> roomAdvantages = new ArrayList<>();
}
