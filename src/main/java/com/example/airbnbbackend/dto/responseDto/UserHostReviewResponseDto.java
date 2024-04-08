package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.HostReview;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserHostReviewResponseDto {

    private String nickname;
    private String content;
    private LocalDateTime writeAt;


    public static UserHostReviewResponseDto of(HostReview hostReview){
        return new UserHostReviewResponseDto(
                hostReview.getHost().getUser().getNickname(),
                hostReview.getContent(),
                hostReview.getWriteAt()
        );
    }
}
