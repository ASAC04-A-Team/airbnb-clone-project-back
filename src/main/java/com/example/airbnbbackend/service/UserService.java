package com.example.airbnbbackend.service;

import com.example.airbnbbackend.dto.responseDto.UserIntroductionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {


    public UserIntroductionResponseDto findAllUserIntroduction(Long userId){


    }

}
