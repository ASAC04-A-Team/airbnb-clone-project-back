package com.example.airbnbbackend.domain;

import com.example.airbnbbackend.domain.common.RoomAdvantage;
import com.example.airbnbbackend.domain.common.RoomCategory;
import com.example.airbnbbackend.domain.common.RoomComfort;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Room {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String address;

    @Getter
    @Column(nullable = false)
    private String nation;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Getter
    @Column(nullable = false)
    private String eachGuestPrice;

    @Column(nullable = false)
    private String extraPetPrice;

    @Column(nullable = false)
    private String introduction;

    @Getter
    @Column(nullable = false)
    private Boolean guestPreference;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Getter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> roomImages = new ArrayList<>();


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomOccupation> roomOccupations = new ArrayList<>();

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomDetail roomDetail;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomCategory> roomCategory;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomComfort> roomComfort;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomAdvantage> roomAdvantage;

    public String getHostName(){
        return getHost().getUser().getNickname();
    }

}
