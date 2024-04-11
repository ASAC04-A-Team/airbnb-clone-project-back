package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Host;
import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.RoomDetail;
import com.example.airbnbbackend.domain.User;
import com.example.airbnbbackend.domain.common.Advantage;
import com.example.airbnbbackend.domain.common.Comfort;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomResponseDto {

    private Long roomId;

    private String roomName;

    private List<String> roomImageUrls;

    private String nation;

    private String address;

    private Integer bathroomCount;

    private Integer bedroomCount;

    private Integer bedCount;

    private Integer capacity;

    private String hostName;

    private String introduction;

    private String price;

    private String description;

    private boolean guestPreference;




    public static EachRoomResponseDto of(Room room){
        Host host = Optional.ofNullable(room.getHost()).orElseThrow(RuntimeException::new);
        RoomDetail roomDetail = Optional.ofNullable(room.getRoomDetail()).orElseThrow(RuntimeException::new);
        return new EachRoomResponseDto(
                room.getId(),
                room.getName(),
                room.getRoomImages(),
                room.getNation(),
                room.getAddress(),
                roomDetail.getBathroomCount(),
                roomDetail.getBedroomCount(),
                roomDetail.getBedCount(),
                roomDetail.getCapacity(),
                host.getHostName(),
                room.getIntroduction(),
                room.getEachGuestPrice(),
                room.getDescription(),
                room.getGuestPreference()
        );
    }
}
