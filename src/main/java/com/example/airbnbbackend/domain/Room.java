package com.example.airbnbbackend.domain;

import com.example.airbnbbackend.domain.common.*;
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

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String address;

    @Getter
    @Column(nullable = false)
    private String nation;


    @Getter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Getter
    @Column(nullable = false)
    private String eachGuestPrice;

    @Column(nullable = false)
    private String extraPetPrice;

    @Getter
    @Column(nullable = false)
    private String introduction;

    @Getter
    @Column(nullable = true)
    private Boolean guestPreference;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    @Getter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Getter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> roomImages = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomOccupation> roomOccupations = new ArrayList<>();

    @Getter
    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomDetail roomDetail;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomCategory> roomCategory= new ArrayList<>();

    @Getter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomComfort> roomComforts= new ArrayList<>();


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomAdvantage> roomAdvantages;

    /* 연관관계 메서드 */
    public List<String> getRoomImages() {
        List<String> roomImages = new ArrayList<>();
        for(RoomImage roomImage : this.roomImages) {
            roomImages.add(roomImage.getImageUrl());
        }
        return roomImages;
    }

    public List<Comfort> getRoomComforts() {
        List<Comfort> roomComforts = new ArrayList<>();
        for(RoomComfort roomComfort: this.roomComforts){
            roomComforts.add(roomComfort.getComfort());
            System.out.println(roomComfort.getComfort());
        }

        return roomComforts;
    }

    public List<Advantage> getRoomAdvantages() {
        List<Advantage> roomAdvantages = new ArrayList<>();
        for(RoomAdvantage roomAdvantage: this.roomAdvantages){
            roomAdvantages.add(roomAdvantage.getAdvantage());
        }

        return roomAdvantages;
    }

}