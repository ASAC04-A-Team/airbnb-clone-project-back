package com.example.airbnbbackend.domain.common;

import com.example.airbnbbackend.domain.Room;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "room_comfort")
public class RoomComfort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_comfort_id")
    private Long id;

    @OneToMany(mappedBy = "roomComfort", cascade = CascadeType.ALL)
    private List<Comfort> comforts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
