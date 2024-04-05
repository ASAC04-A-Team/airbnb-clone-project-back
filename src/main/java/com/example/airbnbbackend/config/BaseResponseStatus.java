package com.example.airbnbbackend.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BaseResponseStatus {

    /** 401번 전역예외 처리 에러코드*/
    GlOBAL_EXCEPTION(false,401, "전역 예외가 발생했습니다 log 확인."),

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true,200, "요청에 성공하였습니다.");

    private boolean isSuccess;
    private final int code;
    private final String message;
    private HttpStatus status;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
