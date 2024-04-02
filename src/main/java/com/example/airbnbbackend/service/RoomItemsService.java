package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.common.RoomCategory;
import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsDto;
import com.example.airbnbbackend.repository.RoomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomItemsService {

    private final RoomCategoryRepository repository;

    /**
     * 방 번호에 따른 홈 화면 반환
     * @return 리뷰 리스트
     */

    public List<EachHomePageRoomItemsDto> findAllRoomForHomePage(Long categoryId){
        List<RoomCategory> roomCategories = repository.findAllByCategoryId(categoryId);
       return roomCategories.stream().map((eachRoom) ->
                EachHomePageRoomItemsDto.of(eachRoom)).toList();
    }

}
