package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/review")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 방 Id 기반 리뷰 리스트 반환 API
     * @param roomId
     * @return 리뷰 리스트
     */
    @GetMapping("/{roomId}")
    public List<EachRoomReviewResponseDto> getRoomReviews(@PathVariable Long roomId) {
        return reviewService.findAllRoomReviews(roomId);
    }
}
