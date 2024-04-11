package com.example.airbnbbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Kakao LogIn", description = "Kakao login API")
public class KakaoLoginController {

    /**사용자가 카카오 아이디, 비밀번호를 옳게 써서 로그인 버튼을 누르면 카카오 서버에서는
     올바른 사용자라는 의미에서  redirectURL로 인가코드를 보내줄 것임  인가코드를 통해 Accecc Token을 요청할 수 있음*/


    // 아래는 redirectURL임
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){

//        RestTemplate rt= new RestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add();
//
//
//        MultiMap<String,String>

        return "카카오 인증완료";
    }
}
