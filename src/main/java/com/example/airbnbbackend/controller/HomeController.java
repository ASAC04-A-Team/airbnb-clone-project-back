package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.common.Constants;
import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsResponseDto;
import com.example.airbnbbackend.common.exception.customException.NotFoundException;
import com.example.airbnbbackend.service.RoomCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{categoryId}")
    public ResponseEntity<Map<String, Object>> getEachRoomItems(@PathVariable("categoryId") Long categoryId ){
        Map<String, Object> roomItem = new HashMap<>();

        try {
            List<EachHomePageRoomItemsResponseDto> roomItems = roomCategoryService.findAllRoomForHomePage(categoryId);
            roomItem.put("roomItem", roomItems);
            return ResponseEntity.ok(roomItem);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new NotFoundException(Constants.ExceptionClass.ROOM_CATEGORY, e.getMessage());
        }
    }
}
