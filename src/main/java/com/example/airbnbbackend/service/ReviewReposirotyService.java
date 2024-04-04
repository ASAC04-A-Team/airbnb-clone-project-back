package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewReposirotyService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAllRoomReviews(Long roomId) {
        List<Review> reviews = reviewRepository.findReviewByRoomId(roomId).orElse((Collections.emptyList()));
        if(CollectionUtils.isEmpty(reviews)){
            throw new RuntimeException("해당 Room : " + roomId + "은 리뷰가 없습니다");
        }
        return reviews;
    }
}
