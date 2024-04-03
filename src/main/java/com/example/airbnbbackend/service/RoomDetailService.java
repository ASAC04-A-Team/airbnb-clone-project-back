package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.common.RoomAdvantage;
import com.example.airbnbbackend.domain.common.RoomComfort;
import com.example.airbnbbackend.dto.responseDto.EachRoomAdvantageResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomComfortResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomResponseDto;
import com.example.airbnbbackend.repository.RoomAdvantageRepository;
import com.example.airbnbbackend.repository.RoomComfortRepository;
import com.example.airbnbbackend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomDetailService {

    private final RoomRepository roomRepository;
    private final RoomComfortRepository roomComfortRepository;
    private final RoomAdvantageRepository roomAdventageRepository;

    public EachRoomResponseDto getRoomId(Long roomId) {
        Room roomDetail = roomRepository.getById(roomId);
        return EachRoomResponseDto.of(roomDetail);
    }


    public List<EachRoomComfortResponseDto> getRoomComfort(Long roomId) {
        List<RoomComfort> roomComfort = roomComfortRepository.findAllByRoomId(roomId);
        return roomComfort.stream().map((eacahRoomComfort)->
             EachRoomComfortResponseDto.of(eacahRoomComfort.getComfort())
        ).toList();
    }

    public List<EachRoomAdvantageResponseDto> getRoomAdvantage(Long roomId){
        List<RoomAdvantage> roomAdvantage = roomAdventageRepository.findAllByRoomId(roomId);
        return  roomAdvantage.stream().map((eachRoomAdvantage)->
                EachRoomAdvantageResponseDto.of(eachRoomAdvantage.getAdvantage())
        ).toList();

    }
}

