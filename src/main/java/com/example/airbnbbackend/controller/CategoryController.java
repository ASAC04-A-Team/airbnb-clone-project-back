package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachCategoryResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsResponseDto;
import com.example.airbnbbackend.dto.responseDto.EachRoomReviewResponseDto;
import com.example.airbnbbackend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 방 Id 기반 리뷰 리스트 반환 API
     * @return 리뷰 리스트
     */
    @Operation(summary = "Get all CategoryData")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EachCategoryResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "카테고리 정보가 없는 경우",
                                            value = "{\"isSuccess\": false, \"code\": \"404\", \"message\": \"요청하신 정보를 찾을 수 없습니다.\"}")
                            }))
    })
    @GetMapping("/")
    public List<EachCategoryResponseDto> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
