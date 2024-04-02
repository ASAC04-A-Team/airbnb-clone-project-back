package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.RoomImage;
import com.example.airbnbbackend.domain.common.RoomCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachHomePageRoomItemsDto {

    private Long id;

    private List<String> slides;

    private String host;

    private Boolean gestPreference;

    private String price;

    private String address;

    private String nation;

    public static EachHomePageRoomItemsDto of(RoomCategory roomCategory){
        List<String> homeRoomImageUrls = roomCategory.getRoom().getRoomImages().stream().map(RoomImage::getImageUrl).toList();
        return new EachHomePageRoomItemsDto(
                roomCategory.getRoom().getId(),
                homeRoomImageUrls,
                roomCategory.getRoom().getHost().getUser().getNickname(),
                roomCategory.getRoom().getGestPreference(),
                roomCategory.getRoom().getEachGuestPrice(),
                roomCategory.getRoom().getAddress(),
                roomCategory.getRoom().getNation()
        );
    }
}
