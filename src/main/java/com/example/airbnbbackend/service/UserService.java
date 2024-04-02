package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.HostReview;
import com.example.airbnbbackend.domain.UserInterest;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserHostReviewResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserIntroductionResponseDto;
import com.example.airbnbbackend.repository.UserHostReviewsRepository;
import com.example.airbnbbackend.repository.UserInterestRepository;
import com.example.airbnbbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserInterestRepository userInterestRepository;
    private final UserHostReviewsRepository userHostReviewsRepository;

    public UserIntroductionResponseDto findAllUserIntroduction(Long userId){

        UserInterest userInterest = userInterestRepository.findUserInterestByUserId(userId);
        UserIntroductionResponseDto userIntroductionResponseDto = UserIntroductionResponseDto.of(userInterest);

        return userIntroductionResponseDto;
    }


    public List<UserHostReviewResponseDto> findUserHostReviews(Long userId){
        List<HostReview> userHostReviews = userHostReviewsRepository.findHostReviewsByUserId(userId);

        return userHostReviews.stream().map(
                (eachUserHostReview) -> UserHostReviewResponseDto.of(eachUserHostReview)).toList();
    }


}
