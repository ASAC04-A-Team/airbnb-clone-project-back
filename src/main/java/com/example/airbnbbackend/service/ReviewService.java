package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewSummaryResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepositoryService reviewRepositoryService;
    private final ReviewRepository reviewRepository;

    /**
     * 방 번호에 따른 리뷰리스트 반환
     * @param roomId
     * @return 리뷰 리스트
     */
    public List<EachRoomReviewResponseDto> findAllRoomReviews(Long roomId) {
        List<Review> reviews = reviewRepositoryService.findAllRoomReviews(roomId);
        return reviews.stream()
                .map(EachRoomReviewResponseDto::of)
                .toList();
    }

    /**
     * 방 번호에 따른 통계(리뷰 개수 & 리뷰 평균 점수) 반환
     * @param roomId
     * @return 리뷰 개수 및 평균 점수 반환
     */
    public EachRoomReviewSummaryResponseDto findAllRoomReviewsStatistics(Long roomId) {
        List<Review> reviews = reviewRepositoryService.findAllRoomReviews(roomId);
        return EachRoomReviewSummaryResponseDto.of(reviews);
    }

    public List<EachRoomReviewResponseDto> findRoomReviewSearch(Long roomId, Optional<String> content){
        List<Review> reviewSearch = reviewRepository.findReviewSearch(roomId, content).orElse(Collections.emptyList());
        log.info(reviewSearch.toString());
        if(CollectionUtils.isEmpty(reviewSearch)){
            throw new RuntimeException("해당 Room Reviw: " + roomId+ "은 검색이 되지 않습니다.");
        }

        return reviewSearch.stream().map(EachRoomReviewResponseDto::of).toList();
    }



}
