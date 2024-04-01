package com.example.airbnbbackend.domain.common;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<RoomCategory> roomCategories = new ArrayList<>();
}
