package com.example.airbnbbackend.domain.common;

import jakarta.persistence.*;
import lombok.Getter;
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

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String imageUrl;

    @Getter
    @Column(nullable = false)
    private String description;

    @Getter
    @OneToMany(mappedBy = "advantage", cascade = CascadeType.ALL)
    private List<RoomAdvantage> roomAdvantages = new ArrayList<>();
}
