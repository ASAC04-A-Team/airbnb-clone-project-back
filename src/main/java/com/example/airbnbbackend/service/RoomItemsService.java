package com.example.airbnbbackend.service;

import com.example.airbnbbackend.dto.responseDto.EachRoomItemsDto;
import com.example.airbnbbackend.repository.RoomItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomItemsService {

    private final RoomItemsRepository repository;

    /**
     * 방 번호에 따른 홈 화면 반환
     * @return 리뷰 리스트
     */

    public List<EachRoomItemsDto> findAllRoomForHomePage(){
       return repository.findAll().stream().map((eachRoom) ->
                EachRoomItemsDto.of(eachRoom)).toList();
    }

}
