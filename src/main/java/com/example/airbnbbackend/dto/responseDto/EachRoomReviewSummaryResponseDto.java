package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.IntSummaryStatistics;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomReviewSummaryResponseDto {

    private Integer reviewsCount;

    private Double reviewsAvg;

    public static EachRoomReviewSummaryResponseDto of(List<Review> reviews){
        IntSummaryStatistics statistics = reviews
                .stream()
                .mapToInt(Review::getScore)
                .summaryStatistics();

        return new EachRoomReviewSummaryResponseDto(
                reviews.size(),
                statistics.getAverage()
        );
    };
}
