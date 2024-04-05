package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.dto.responseDto.EachRoomReiviewSummaryResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewReposirotyService reviewReposirotyService;

    /**
     * 방 번호에 따른 리뷰리스트 반환
     * @param roomId
     * @return 리뷰 리스트
     */
    public List<EachRoomReviewResponseDto> findAllRoomReviews(Long roomId) {
        List<Review> reviews = reviewReposirotyService.findAllRoomReviews(roomId);
        return reviews.stream()
                .map(EachRoomReviewResponseDto::of)
                .toList();
    }

    /**
     * 방 번호에 따른 통계(리뷰 개수 & 리뷰 평균 점수) 반환
     * @param roomId
     * @return 리뷰 개수 및 평균 점수 반환
     */
    public EachRoomReiviewSummaryResponseDto findAllRoomReviewsStatistics(Long roomId) {
        List<Review> reviews = reviewReposirotyService.findAllRoomReviews(roomId);
        return EachRoomReiviewSummaryResponseDto.of(reviews);
    }
}
