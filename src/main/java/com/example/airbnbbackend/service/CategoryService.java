package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.domain.common.Category;
import com.example.airbnbbackend.dto.responseDto.EachCategoryResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    /**
     * 모든 카테고리 리스트 반환
     * @return 카테고리 리스트
     */
    public List<EachCategoryResponseDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((eachCategory) ->
                        EachCategoryResponseDto.of(eachCategory))
                .toList();
    }
}
