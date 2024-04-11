package com.example.airbnbbackend.service;

import com.example.airbnbbackend.common.Constants;
import com.example.airbnbbackend.common.exception.customException.EmptyResourceException;
import com.example.airbnbbackend.domain.HostReview;
import com.example.airbnbbackend.domain.User;
import com.example.airbnbbackend.domain.UserInterest;
import com.example.airbnbbackend.dto.responseDto.*;
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

import static com.example.airbnbbackend.common.Constants.ExceptionClass.USER;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserInterestRepository userInterestRepository;
    private final UserHostReviewsRepository userHostReviewsRepository;
    private final UserRepository userRepository;

    public UserIntroductionResponseDto findAllUserIntroduction(Long userId){

        UserInterest userInterest =
                userInterestRepository.findUserInterestByUserId(userId).orElseThrow(()-> new EmptyResourceException(USER," DB에 해당 userInterest가 존재하지 않습니다."));

        UserIntroductionResponseDto userIntroductionResponseDto = UserIntroductionResponseDto.of(userInterest);

        if(userIntroductionResponseDto == null){
            new EmptyResourceException(USER,"유저 소개 DTO 생성을 실패했습니다.");
        }

        return userIntroductionResponseDto;
    }

    public List<UserHostReviewResponseDto> findUserHostReviews(Long userId){
        List<HostReview> userHostReviews = userHostReviewsRepository.findHostReviewsByUserId(userId).orElseThrow(()-> new EmptyResourceException(USER,"DB에 해당 hostReview가 존재하지 않습니다"));

        return userHostReviews.stream()
                .map(eachUserHostReview -> {
                    int year = eachUserHostReview.getWriteAt().getYear();
                    int month = eachUserHostReview.getWriteAt().getMonthValue();
                    String stringYear = String.valueOf(year);
                    String stringMonth = String.valueOf(month);
                    return UserHostReviewResponseDto.of(eachUserHostReview, stringYear, stringMonth);
                })
                .toList();}

    public UserInformationResponseDto findAllUserInformation(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EmptyResourceException(USER,"존재하지 않는 유저입니다"));

        /** User의 Host 리뷰 수 */
        int hostReviewsNums = userHostReviewsRepository.findHostReviewsNumsByUserId(userId).orElseThrow(()-> new EmptyResourceException(USER,"리뷰를 가져오지 못했습니다"));

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
        UserInformationResponseDto userInformationResponseDto = UserInformationResponseDto.of(user, hostReviewsNums, sinceRegisterAt, separator);

        if(userInformationResponseDto == null){
            new EmptyResourceException(USER,"유저 정보 DTO 생성을 실패했습니다.");
        }
        return userInformationResponseDto;
    }

    public UserAuthInformationDto findUserAuthInformation(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EmptyResourceException(USER,"존재하지 않는 유저입니다"));

        UserAuthInformationDto userAuthInformationDto = UserAuthInformationDto.of(user);

        if(userAuthInformationDto == null){
            new EmptyResourceException(USER,"유저 인증 정보 DTO 생성을 실패했습니다.");
        }

        return userAuthInformationDto;
    }

}