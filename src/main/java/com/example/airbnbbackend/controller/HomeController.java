package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsResponseDto;
import com.example.airbnbbackend.service.RoomCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        } catch (ResponseStatusException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build(); //아무것도 반환하지 않는다.
        } catch (Exception e) {
            log.info("카테고리 ID에 맞는 Room 리스트를 가져오던 중 에러 발생: {}", categoryId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}
