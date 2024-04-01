package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Review;
import com.example.airbnbbackend.domain.common.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EachCategoryResponseDto {
    private Long categoryId;

    private String name;

    private String image_url;

    public static EachCategoryResponseDto of(Category category) {
        return new EachCategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getImageUrl()
        );
    }

}
