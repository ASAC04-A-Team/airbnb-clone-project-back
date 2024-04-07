package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.common.Constants;
import com.example.airbnbbackend.common.dto.BaseResponse;
import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsResponseDto;
import com.example.airbnbbackend.common.exception.customException.EmptyResourceException;
import com.example.airbnbbackend.service.RoomCategoryService;
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
public class HomeController {

    private final RoomCategoryService roomCategoryService;

    /**
     * 홈페이지 카테고리 별 RoomItem 리스트 반환 API
     * @param categoryId
     * @return roomItems by categoryId
     */
    @GetMapping("/{categoryId}")
    public BaseResponse<List<EachHomePageRoomItemsResponseDto>> getEachRoomItems(@PathVariable("categoryId") Long categoryId ){
        try {
            List<EachHomePageRoomItemsResponseDto> roomItems = roomCategoryService.findAllRoomForHomePage(categoryId);
            return BaseResponse.success(roomItems);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new EmptyResourceException(Constants.ExceptionClass.ROOM_CATEGORY, e.getMessage());
        }
    }
}
