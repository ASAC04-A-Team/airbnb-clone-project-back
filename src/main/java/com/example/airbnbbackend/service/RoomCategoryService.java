package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Room;
import com.example.airbnbbackend.domain.common.RoomCategory;
import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsResponseDto;
import com.example.airbnbbackend.repository.RoomCategoryRepository;
import com.example.airbnbbackend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomCategoryService {

    private final RoomCategoryRepository roomCategoryRepository;
    private final RoomRepository roomRepository;

    /**
     * 방 번호에 따른 홈 화면 반환
     * @return 리뷰 리스트
     */
    public List<EachHomePageRoomItemsResponseDto> findAllRoomForHomePage(Long categoryId){
        List<RoomCategory> roomCategories = roomCategoryRepository.findAllByCategoryId(categoryId)
                .orElse(Collections.emptyList());

//        if (roomCategories.isEmpty()) {
//            throw new RuntimeException("카테고리Id: " + categoryId + "에 맞는 방을 찾을 수 없습니다.");
//        }

        // 해당 카테고리를 가지는 방 리스트 가져오기
        List<Optional<Room>> rooms = roomCategories.stream()
                .map(EachRoomCategory -> roomRepository.findById(EachRoomCategory.getRoom().getId()))
                .toList();

        return rooms.stream()
                .filter(Optional::isPresent) // 값이 존재하는 Optional만 필터링
                .map(Optional::get) // Optional에서 값(room) 추출
                .map(EachHomePageRoomItemsResponseDto::of)
                .collect(Collectors.toList());
    }

}
