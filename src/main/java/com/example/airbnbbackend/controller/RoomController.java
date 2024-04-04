package com.example.airbnbbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

//    private final RoomService roomService;

    /**
     * 숙소 상세 페이지 : 숙소 정보 반환 API
     * @param id
     * @return
     */
//    @GetMapping("/roomDetail/{id}")
//    public EachRoomResponseDto getRoomPage(@PathVariable Long id) {
//
//        return roomService.findByRoomId(id);
//    }

    /**
     * 홈 페이지 : 숙소 정보 리스트 반환 API
     */
//    @GetMapping("/")
//    public List<HomepageRoomResponseDto> getRoomPage() {
//    }


}
