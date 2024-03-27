package com.example.airbnbbackend.domain.common;

import com.example.airbnbbackend.domain.Room;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "room_advantage")
public class RoomAdvantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_advantage_id")
    private Long id;

    @OneToMany(mappedBy = "room_advantage_id", cascade = CascadeType.ALL)
    private List<Advantage> advantages = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
