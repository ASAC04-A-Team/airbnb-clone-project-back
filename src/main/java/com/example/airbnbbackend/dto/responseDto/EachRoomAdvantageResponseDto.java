package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.common.Advantage;
import com.example.airbnbbackend.domain.common.Comfort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class EachRoomAdvantageResponseDto {

    private String name;
    private String imageUrl;
    private String description;

    public static EachRoomAdvantageResponseDto of(Advantage advantage){
        return new EachRoomAdvantageResponseDto(
                advantage.getName(),
                advantage.getImageUrl(),
                advantage.getDescription()
        );
    }
}
