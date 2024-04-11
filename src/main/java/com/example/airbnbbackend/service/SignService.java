package com.example.airbnbbackend.service;

import com.example.airbnbbackend.domain.User;
import com.example.airbnbbackend.dto.requestDto.auth.CheckCertificationRequestDto;
import com.example.airbnbbackend.dto.requestDto.auth.EmailCertificationRequestDto;
import com.example.airbnbbackend.dto.responseDto.auth.CheckCertificationResponseDto;
import com.example.airbnbbackend.dto.responseDto.auth.EmailCertificationResponseDto;
import com.example.airbnbbackend.dto.responseDto.auth.SignInResultDto;
import com.example.airbnbbackend.dto.responseDto.auth.SignUpResultDto;
import com.example.airbnbbackend.service.exception.UserDuplicateException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

public interface SignService {
    // 부모도 받을 수 있게 해줌.

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);


    SignUpResultDto signUpResult(EmailCertificationRequestDto dto);

    SignInResultDto signInResult(String email, String password) throws RuntimeException;

    User signUp(String email, String password, String nickname, String nation, String address, LocalDateTime registerAt, String email_auth_code, boolean isHost, boolean isAuth, String userToken) throws UserDuplicateException;


    User login(String email, String password) throws UsernameNotFoundException, BadCredentialsException, Throwable;
}
