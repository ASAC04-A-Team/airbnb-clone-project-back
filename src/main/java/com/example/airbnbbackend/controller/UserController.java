package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.UserHostReviewResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserInformationResponseDto;
import com.example.airbnbbackend.dto.responseDto.UserIntroductionResponseDto;
import com.example.airbnbbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /* Todo User 사용자 소개 조회 API -userId를 받으면 user_interest Table을 가져오는 API*/
    // nickname,residence,hobby
    @GetMapping("/introduction/{userId}")
    public UserIntroductionResponseDto getUserIntroduction(@PathVariable("userId") Long userId ){
        UserIntroductionResponseDto UserIntroduction = userService.findAllUserIntroduction(userId);
        return UserIntroduction;
    }

    /* Todo User 사용자 후기 조회 API -userId를 받으면 해당 User의 host_review Table을 List형식으로 가져오는 API */
    //content, host nickname,작성일
    @GetMapping("/hostReview/{userId}")
    public List<UserHostReviewResponseDto> getUserHostReviews(@PathVariable("userId") Long userId){
        List<UserHostReviewResponseDto> userHostReviews = userService.findUserHostReviews(userId);
        return userHostReviews;
    }

    /* Todo User 사용자 정보 조회 API */
    // nickname, review 수, 가입년수, userProfile Image, host 여부
    @GetMapping("/information/{userId}")
    public UserInformationResponseDto getUserInformation(@PathVariable("userId") Long userId){
        UserInformationResponseDto UserInformation = userService.findAllUserInformation(userId);
        return UserInformation;
    }



    /* Todo User 사용자 인증 정보 조회 API */
}
