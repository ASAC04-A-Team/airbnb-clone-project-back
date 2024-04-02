package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Room;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachHomePageRoomItemsResponseDto {

    private Long id;

    private List<String> roomImageUrls;

    private String host;

    private Boolean guestPreference;

    private String price;

    private String address;

    private String nation;

    public static EachHomePageRoomItemsResponseDto of(Room room){
        return new EachHomePageRoomItemsResponseDto(
                room.getId(),
                room.getRoomImages(),
                room.getHost().getHostName(),
                room.getGuestPreference(),
                room.getEachGuestPrice(),
                room.getAddress(),
                room.getNation()
        );
    }
}
