package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.common.dto.BaseResponse;
import com.example.airbnbbackend.dto.responseDto.*;
import com.example.airbnbbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    /* Todo User 사용자 소개 조회 API -userId를 받으면 user_interest Table을 가져오는 API*/
    // nickname,residence,hobby
    @Operation(summary = "Get User Introduction data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserIntroductionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "User Introduction 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/introduction/{userId}")
    public UserIntroductionResponseDto getUserIntroduction(@PathVariable("userId") Long userId ){


        UserIntroductionResponseDto userIntroduction = userService.findAllUserIntroduction(userId);

        return userIntroduction;
    }

    /* Todo User 사용자 후기 조회 API -userId를 받으면 해당 User의 host_review Table을 List형식으로 가져오는 API */
    //content, host nickname,작성일
    @Operation(summary = "Get all HostReviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserHostReviewResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "호스트 리뷰 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/hostReview/{userId}")
    public List <UserHostReviewResponseDto> getUserHostReviews(@PathVariable("userId") Long userId){
        List<UserHostReviewResponseDto> userHostReviews = userService.findUserHostReviews(userId);
        return userHostReviews;
    }

    /* Todo User 사용자 정보 조회 API */
    // nickname, review 수, 가입년수, userProfile Image, host 여부
    @Operation(summary = "Get User information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserInformationResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "User information 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 사용자 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/information/{userId}")
    public UserInformationResponseDto getUserInformation(@PathVariable("userId") Long userId){
        UserInformationResponseDto userInformation = userService.findAllUserInformation(userId);
        return userInformation;
    }

//    /* Todo User 사용자 인증 정보 조회 API */

    @Operation(summary = "Get User Auth information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = "{\"userAuth\": true}")
                            })}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "User auth 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 사용자 권한 정보를 찾을 수 없습니다.\"}")
                            }))
    })

    /* Todo User 사용자 인증 정보 조회 API */
    @GetMapping("/authInformation/{userId}")
    public UserAuthInformationDto getUserAuthInformation(@PathVariable("userId") Long userId){
        UserAuthInformationDto userAuthInformation = userService.findUserAuthInformation(userId);
        return userAuthInformation;
    }
}
