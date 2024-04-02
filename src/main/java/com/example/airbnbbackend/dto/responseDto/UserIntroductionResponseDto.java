package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.domain.UserInterest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserIntroductionResponseDto {

    private String nickname;

    private String hobby;

    private String residence;


    public static UserIntroductionResponseDto of(UserInterest userInterest) {
        return new UserIntroductionResponseDto(
                userInterest.getUser().getNickname(),
                userInterest.getHobby(),
                userInterest.getResidence()
        );
    }

}
