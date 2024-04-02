package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.responseDto.EachHomePageRoomItemsDto;
import com.example.airbnbbackend.service.RoomItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final RoomItemsService roomItemsService;

    @GetMapping("/{categoryId}")
    public List<EachHomePageRoomItemsDto> getEachRoomItems(@PathVariable("categoryId") Long categoryId ){
        return roomItemsService.findAllRoomForHomePage(categoryId);
    }
}
