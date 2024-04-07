package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.common.Constants;
import com.example.airbnbbackend.common.dto.BaseResponse;
import com.example.airbnbbackend.common.exception.CustomExceptionHandler;
import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsResponseDto;
import com.example.airbnbbackend.common.exception.customException.EmptyResourceException;
import com.example.airbnbbackend.service.RoomCategoryService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Home", description = "Home API")
public class HomeController {

    private final RoomCategoryService roomCategoryService;

    /**
     * 홈페이지 카테고리 별 RoomItem 리스트 반환 API
     *
     * @param categoryId
     * @return roomItems by categoryId
     */
    @Operation(summary = "Get Homepage Room Items by categoryId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EachHomePageRoomItemsResponseDto.class)))}),
            @ApiResponse(responseCode = "12", description = "해당 카테고리 Id에 해당 CategoryId에 해당하는 방 정보가 존재하지 않습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "방 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"12\", \"message\": \"Result Empty, 값이 비어있습니다.\"}")
                            }))
    })
    @GetMapping("/{categoryId}")
    public BaseResponse<List<EachHomePageRoomItemsResponseDto>> getEachRoomItems(@PathVariable Long categoryId) {
        try {
            List<EachHomePageRoomItemsResponseDto> roomItems = roomCategoryService.findAllRoomForHomePage(categoryId);
            return BaseResponse.success(roomItems);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new EmptyResourceException(Constants.ExceptionClass.ROOM_CATEGORY, e.getMessage());
        }
    }
}
