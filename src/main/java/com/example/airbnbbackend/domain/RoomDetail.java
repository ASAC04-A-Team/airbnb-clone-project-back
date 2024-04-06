package com.example.airbnbbackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "room_detail")
public class RoomDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_detail_id")
    private Long id;

    @Column(nullable = false)
    private Integer bathroomCount;

    @Column(nullable = false)
    private Integer bedroomCount;

    @Column(nullable = false)
    private Integer bedCount;

    @Column(nullable = false)
    private Integer capacity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
