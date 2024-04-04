package com.example.airbnbbackend;


import com.example.airbnbbackend.config.BaseException;
import com.example.airbnbbackend.config.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.airbnbbackend.config.BaseResponseStatus.GlOBAL_EXCEPTION;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = BaseException.class)
//    public ResponseEntity<Object> ExceptionHandler(BaseException e) {
//        //log.error("MethodArgumentNotValidException", e);
//        return new ResponseEntity<>(new BaseResponse<>(e.getStatus()),HttpStatus.NOT_FOUND);
//    }

    //예외 전역 처리
    /*TODO 로그 파일 생성하기*/
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = Exception.class)
    public BaseResponse ExceptionHandler(Exception e) {
        log.error("MethodArgumentNotValidException", e);
        return new BaseResponse(GlOBAL_EXCEPTION);
    }



}

