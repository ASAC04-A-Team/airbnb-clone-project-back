package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.common.Advantage;
import com.example.airbnbbackend.domain.common.Comfort;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomResponseDto {

    private Long roomId;

    private String roomName;

    private List<String> roomImageUrls;

    private String nation;

    private String address;

    private Integer bathroomCount;

    private Integer bedRoomCount;

    private Integer bedCount;

    private Integer capacity;

    private Long reviewCount;

    private String hostName;

    private String price;

    private String description;




    public static EachRoomResponseDto of(Room room){
        return new EachRoomResponseDto(
                room.getId(),
                room.getName(),
                room.getRoomImages(),
                room.getNation(),
                room.getAddress(),
                room.getRoomDetail().getBathroomCount(),
                room.getRoomDetail().getBedroomCount(),
                room.getRoomDetail().getBedCount(),
                room.getRoomDetail().getCapacity(),
                room.getReviews().stream().count(),
                room.getHost().getHostName(),
                room.getEachGuestPrice(),
                room.getDescription()

        );
    }
}
