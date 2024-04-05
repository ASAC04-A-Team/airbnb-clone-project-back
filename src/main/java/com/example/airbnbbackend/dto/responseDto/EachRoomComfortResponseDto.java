package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.common.Comfort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomComfortResponseDto {

    private String name;
    private String imageUrl;

    public static EachRoomComfortResponseDto of(Comfort comfort){
        return new EachRoomComfortResponseDto(
                comfort.getName(),
                comfort.getImageUrl()
        );
    }
}
