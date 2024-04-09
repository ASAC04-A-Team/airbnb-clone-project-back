package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthInformationDto {

    private String nickname;
    private Boolean isAuth;

    public static UserAuthInformationDto of(User user){
        return new UserAuthInformationDto(
                user.getNickname(),
                user.getIsAuth()
        );
    }
}
