package com.example.airbnbbackend.dto.responseDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserIntroductionResponseDto {

    private String nickname;

    private String hobby;

    private String residence;

}
