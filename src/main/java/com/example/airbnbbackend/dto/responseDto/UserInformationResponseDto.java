package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInformationResponseDto {
        // nickname, review 수, 가입년수, userProfile Image, host 여부

        private String nickname;
        private String profileImageUrl;
        private Boolean isHost;
        private int reviewsNum;
        private int sinceRegistration;
        private String separator;

        public static  UserInformationResponseDto of(User user, int reviewsNum, int sinceRegistration, String separator){
            return new UserInformationResponseDto(
                    user.getNickname(),
                    user.getProfileImageUrl(),
                    user.getIsHost(),
                    reviewsNum,
                    sinceRegistration,
                    separator
            );
        }
}
