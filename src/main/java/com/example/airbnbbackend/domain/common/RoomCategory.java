package com.example.airbnbbackend.domain.common;

import com.example.airbnbbackend.domain.Likes;
import com.example.airbnbbackend.domain.Room;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "room_category")
public class RoomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_category_id")
    private Long id;


    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
