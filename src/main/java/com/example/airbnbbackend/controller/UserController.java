package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.UserIntroductionResponseDto;
import com.example.airbnbbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /* Todo User 사용자 소개 조회 API - 특정 userId를 받으면 user_interest Table을 가져오는 API임*/
    @GetMapping("/introduction/{userId}")
    public UserIntroductionResponseDto getUserIntroduction(@PathVariable("userId") Long userId ){
        UserIntroductionResponseDto UserIntroduction = userService.findAllUserIntroduction(userId);
        return UserIntroduction;
    }


    // nickname,hobby,residence

    /* Todo User 사용자 정보 조회 API */
    // nickname, review 수, 가입년수, userProfile Image
    /* Todo User 사용자 후기 조회 API */
    //content, host_id

    /* Todo User 사용자 인증 정보 조회 API */
}
