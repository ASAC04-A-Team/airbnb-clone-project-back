package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.HostReview;
import com.example.airbnbbackend.domain.User;
import com.example.airbnbbackend.domain.UserInterest;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserHostReviewResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserInformationResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserIntroductionResponseDto;
import com.example.airbnbbackend.repository.UserHostReviewsRepository;
import com.example.airbnbbackend.repository.UserInterestRepository;
import com.example.airbnbbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserInterestRepository userInterestRepository;
    private final UserHostReviewsRepository userHostReviewsRepository;
    private final UserRepository userRepository;

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

    public UserInformationResponseDto findAllUserInformation(Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        /** User의 Host 리뷰 수 */
        int hostReviewsNumsByUserId = userHostReviewsRepository.findHostReviewsNumsByUserId(userId);

        LocalDateTime registerAt = user.getRegisterAt();
        LocalDateTime nowRegisterAt = LocalDateTime.now();
        Period diff = Period.between(registerAt.toLocalDate(), nowRegisterAt.toLocalDate());

        int sinceRegisterAt;
        String separator;
        int year = diff.getYears();
        /** User의 가입기간 */
        if (year != 0) {
            sinceRegisterAt = diff.getYears();
            separator = "year";
        } else{
            sinceRegisterAt = diff.getMonths();
            separator = "month";
        }
        return UserInformationResponseDto.of(user,hostReviewsNumsByUserId,sinceRegisterAt,separator);
    }

    public Boolean findUserAuthInformation(Long userId){
        User user = userRepository.findById(userId).orElse(null);

        return user.getIsAuth();
    }

}
