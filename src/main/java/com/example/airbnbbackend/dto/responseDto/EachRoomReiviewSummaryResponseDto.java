package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.IntSummaryStatistics;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachRoomReiviewSummaryResponseDto {

    private Integer reviewsCount;

    private Double reviewsAvg;

    public static EachRoomReiviewSummaryResponseDto of(List<Review> reviews){
        IntSummaryStatistics statistics = reviews
                .stream()
                .mapToInt(Review::getScore)
                .summaryStatistics();

        return new EachRoomReiviewSummaryResponseDto(
                reviews.size(),
                statistics.getAverage()
        );
    };
}
