package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * 방 번호에 따른 리뷰리스트 반환
     * @param roomId
     * @return 리뷰 리스트
     */
    public List<EachRoomReviewResponseDto> findAllRoomReviews(Long roomId) {
        List<Review> reviews = reviewRepository.findReviewByRoomId(roomId);
        return reviews.stream()
                .map(EachRoomReviewResponseDto::of)
                .toList();
    }
}
