package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomReviewResponseDto {

    private Long reviewId;

    private String content;

    private LocalDateTime writeAt;

    private String reviewerName;

    private String reviewerProfileImageUrl;

    private Integer score;

    private String nation;

    public static EachRoomReviewResponseDto of(Review review) {
        User user = Optional.ofNullable(review.getUser())
                .orElseThrow(RuntimeException::new);
        return new EachRoomReviewResponseDto(
                review.getId(),
                review.getContent(),
                review.getWriteAt(),
                user.getNickname(),
                user.getProfileImageUrl(),
                review.getScore(),
                user.getNation()
        );
    }
}
