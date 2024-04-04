package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachHostResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomAdvantageResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomComfortResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomResponseDto;
import com.example.airbnbbackend.service.RoomDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class RoomController {

    private final RoomDetailService roomDetailService;

    /**
     * 숙소 상세 페이지 : 숙소 정보 반환 API
     * @param roomId
     * @return
     */
    @GetMapping("/roomDetail/{roomId}")
    public ResponseEntity<EachRoomResponseDto> getRoomPage(@PathVariable Long roomId) {

        return ResponseEntity.ok(roomDetailService.getRoomId(roomId));
    }

    @GetMapping("/roomComfort/{roomId}")
    public List<EachRoomComfortResponseDto> getRoomComfort(@PathVariable Long roomId){

        return roomDetailService.getRoomComfort(roomId);
    }


    @GetMapping("/roomAdvantage/{roomId}")
    public List<EachRoomAdvantageResponseDto> getRoomAdvantage(@PathVariable Long roomId){

        return roomDetailService.getRoomAdvantage(roomId);
    }

    @GetMapping("/roomHost/{roomId}")
    public EachHostResponseDto getRoomHost(@PathVariable Long roomId){
        return roomDetailService.getRoomHost(roomId);
    }

    /**
     * 홈 페이지 : 숙소 정보 리스트 반환 API
     */
//    @GetMapping("/")
//    public List<HomepageRoomResponseDto> getRoomPage() {
//    }


}
