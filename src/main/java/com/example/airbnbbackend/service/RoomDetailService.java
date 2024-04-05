package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Host;
import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.common.RoomAdvantage;
import com.example.airbnbbackend.domain.common.RoomComfort;
import com.example.airbnbbackend.dto.responseDto.EachHostResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomAdvantageResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomComfortResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomResponseDto;
import com.example.airbnbbackend.repository.HostRepository;
import com.example.airbnbbackend.repository.RoomAdvantageRepository;
import com.example.airbnbbackend.repository.RoomComfortRepository;
import com.example.airbnbbackend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomDetailService {

    private final RoomRepository roomRepository;
    private final RoomComfortRepository roomComfortRepository;
    private final RoomAdvantageRepository roomAdventageRepository;
    private final HostRepository hostRepository;

    public EachRoomResponseDto getRoomId(Long roomId) {
        Optional<Room> roomDetail = roomRepository.findById(roomId);
        if(roomDetail.isEmpty()){
            throw new RuntimeException("해당 방을 찾을 수 없습니다.");
        }
        return EachRoomResponseDto.of(roomDetail.get());
    }


    public List<EachRoomComfortResponseDto> getRoomComfort(Long roomId) {
        List<RoomComfort> roomComfort = roomComfortRepository.findAllByRoomId(roomId).orElse(Collections.emptyList());

        if(CollectionUtils.isEmpty(roomComfort)){
            throw new RuntimeException("해당 Room: " + roomId + "은 편의시설이 존재하지 않습니다.");
        }
        return roomComfort.stream()
                .map((eacahRoomComfort)->
                        EachRoomComfortResponseDto.of(eacahRoomComfort.getComfort())).
                toList();
    }

    public List<EachRoomAdvantageResponseDto> getRoomAdvantage(Long roomId){
        List<RoomAdvantage> roomAdvantage = roomAdventageRepository.findAllByRoomId(roomId).orElse(Collections.emptyList());

        if(CollectionUtils.isEmpty(roomAdvantage)){
            throw new RuntimeException("해당 Room: " + roomId + "은 장점이 존재하지 않습니다.");
        }
        return  roomAdvantage.stream()
                .map((eachRoomAdvantage)->
                EachRoomAdvantageResponseDto.of(eachRoomAdvantage.getAdvantage()))
                .toList();

    }

    public EachHostResponseDto getRoomHost(Long roomId){
        Optional<Host> host = hostRepository.findHostByRoomId(roomId);
        if(host.isEmpty()){
            throw new RuntimeException("해당 Room : " + roomId + "은 호스트가 존재하지 않습니다");
        }
        return EachHostResponseDto.of(host.get());
    }
}

