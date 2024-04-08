package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachRoomReviewSummaryResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.service.ReviewService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/review")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Review", description = "Review API")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 방 Id 기반 리뷰 리스트 반환 API
     * @param roomId
     * @return 리뷰 리스트
     */
    @Operation(summary = "Get Room's review list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EachRoomReviewResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "방 리뷰 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/{roomId}")
    public List<EachRoomReviewResponseDto> getRoomReviews(@PathVariable Long roomId) {
        return reviewService.findAllRoomReviews(roomId);
    }

    /**
     * 방 리뷰 통계정보 반환 API
     * @param roomId
     * @return 방 리뷰 통계 정보 반환
     */
    @Operation(summary = "Get Room review statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EachRoomReviewSummaryResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "방 리뷰 통계 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/reviewsStatistic/{roomId}")
    public EachRoomReviewSummaryResponseDto getRoomReviewStatistic(@PathVariable Long roomId){
        return reviewService.findAllRoomReviewsStatistics(roomId);
    }
}
