package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachCategoryResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    /**
     * 방 Id 기반 리뷰 리스트 반환 API
     * @return 리뷰 리스트
     */
    @GetMapping("/")
    public List<EachCategoryResponseDto> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
