package com.example.airbnbbackend.domain;

import com.example.airbnbbackend.domain.common.RoomAdvantage;
import com.example.airbnbbackend.domain.common.RoomCategory;
import com.example.airbnbbackend.domain.common.RoomComfort;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String nation;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String eachGuestPrice;

    @Column(nullable = false)
    private String extraPetPrice;

    @Column(nullable = false)
    private String introduction;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> roomImages = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomOccupation> roomOccupations = new ArrayList<>();

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomDetail roomDetail;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomCategory roomCategory;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomComfort roomComfort;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomAdvantage roomAdvantage;
}
