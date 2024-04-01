package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.RoomImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomItemsDto {

    private Long roomId;

    private List<RoomImage> imageUrl;

    private String nation;

    private String address;

    private String eachGuestPrice;

    public static EachRoomItemsDto of(Room room){
        return new EachRoomItemsDto(
                room.getId(),
                room.getRoomImages().stream().toList(),
                room.getNation(),
                room.getAddress(),
                room.getEachGuestPrice()
        );
    }
}
