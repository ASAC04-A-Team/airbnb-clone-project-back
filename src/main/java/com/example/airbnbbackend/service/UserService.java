package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.UserInterest;
import com.example.airbnbbackend.dto.responseDto.UserIntroductionResponseDto;
import com.example.airbnbbackend.repository.UserInterestRepository;
import com.example.airbnbbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserInterestRepository userInterestRepository;

    public UserIntroductionResponseDto findAllUserIntroduction(Long userId){

        UserInterest userInterest = userInterestRepository.findUserInterestByUserId(userId);
        UserIntroductionResponseDto userIntroductionResponseDto = UserIntroductionResponseDto.of(userInterest);

        return userIntroductionResponseDto;
    }

}
