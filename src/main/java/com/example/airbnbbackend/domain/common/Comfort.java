package com.example.airbnbbackend.domain.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Comfort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comfort_id")
    private Long id;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "comfort", cascade = CascadeType.ALL)
    private List<RoomComfort> roomComforts = new ArrayList<>();
}
