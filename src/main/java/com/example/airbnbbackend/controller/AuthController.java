
package com.example.airbnbbackend.controller;

import com.example.airbnbbackend.dto.requestDto.auth.CheckCertificationRequestDto;
import com.example.airbnbbackend.dto.requestDto.auth.EmailCertificationRequestDto;
import com.example.airbnbbackend.dto.responseDto.auth.CheckCertificationResponseDto;
import com.example.airbnbbackend.dto.responseDto.auth.EmailCertificationResponseDto;
import com.example.airbnbbackend.dto.responseDto.auth.SignInResultDto;
import com.example.airbnbbackend.dto.responseDto.auth.SignUpResultDto;
import com.example.airbnbbackend.service.SignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users") // 나중에 수정
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final SignService signService;

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Valid EmailCertificationRequestDto requestBody
    ) {

        return signService.emailCertification(requestBody);
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {


        return signService.checkCertification(requestBody);
    }

    // @ApiParam은 swagger 어노테이션
    // 로그인
    @PostMapping("/sign-in")
    public SignInResultDto signIn(
            @RequestParam(value = "ID", required = true) String email,
            @RequestParam(value = "Password", required = true) String password
    ) throws RuntimeException {
        log.info("로그인 시도 -> email : {},  pw : ****", email);
        SignInResultDto signInResultDto = signService.signInResult(email, password);

        if (signInResultDto.getCode() == 0) {
            log.info("정상적으로 로그인 되었습니다 -> email : {}, userToken : {}", email, signInResultDto.getUserToken());
            signInResultDto.getUserToken();
        }
        return signInResultDto;

    }

    // @ApiParam은 swagger 어노테이션
    // 회원가입
    @PostMapping("/sign-up")
    public SignUpResultDto signUp(EmailCertificationRequestDto dto) throws RuntimeException {
        log.info("회원가입을 수행합니다. email : {}, password : ****, nickname : {}, role : {}",
                dto.getEmail(), dto.getNickname(), dto.getNickname());

        SignUpResultDto signUpResultDto = signService.signUpResult(dto);
        log.info("회원가입 완료했습니다");
        return signUpResultDto;
    }


    @GetMapping("/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        log.error("ExceptionHandler 호출 {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", badRequest.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생했습니다");

        return new ResponseEntity<>(map, httpHeaders, badRequest);
    }
}
