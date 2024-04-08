package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.common.dto.BaseResponse;
import com.example.airbnbbackend.dto.responseDto.*;
import com.example.airbnbbackend.service.RoomDetailService;
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
@RequestMapping("/api/room")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Room", description = "Room API")
public class RoomController {

    private final RoomDetailService roomDetailService;

    /**
     * 숙소 상세 페이지 : 숙소 정보 반환 API
     * @param roomId
     * @return
     */
    @Operation(summary = "Get Room Detail data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EachRoomResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Room Detail 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/roomDetail/{roomId}")
    public BaseResponse<EachRoomResponseDto> getRoomPage(@PathVariable Long roomId) {

        return BaseResponse.success(roomDetailService.getRoomId(roomId));
    }

    /**
     * 숙소 편의시설 정보 반환 API
     * @param roomId
     * @return 숙소 편의시설 정보 리스트
     */
    @Operation(summary = "Get Room comfort data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EachRoomComfortResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Room Comfort 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/roomComfort/{roomId}")
    public BaseResponse<List<EachRoomComfortResponseDto>> getRoomComfort(@PathVariable Long roomId){

        return BaseResponse.success(roomDetailService.getRoomComfort(roomId));
    }

    /**
     * 숙소 장점 정보 반환 API
     * @param roomId
     * @return 숙소 장점 리스트
     */
    @Operation(summary = "Get Room advantage data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EachRoomAdvantageResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Room advantage 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/roomAdvantage/{roomId}")
    public BaseResponse<List<EachRoomAdvantageResponseDto>> getRoomAdvantage(@PathVariable Long roomId){

        return BaseResponse.success(roomDetailService.getRoomAdvantage(roomId));
    }

    /**
     * 숙소 호스트 정보 반환 API
     * @param roomId
     * @return 숙소 호스트
     */
    @Operation(summary = "Get Host of the room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EachHostResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Room host 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/roomHost/{roomId}")
    public BaseResponse<EachHostResponseDto> getRoomHost(@PathVariable Long roomId){
        return BaseResponse.success(roomDetailService.getRoomHost(roomId));
    }
}
