package com.example.airbnbbackend.common.exception;

import com.example.airbnbbackend.common.dto.BaseResponse;
import com.example.airbnbbackend.common.exception.customException.EmptyResourceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * NotFound Exception
     * @param e
     * @param request
     * @return 예외정보 ResponseEntity
     */
    @ExceptionHandler(value = EmptyResourceException.class)
    public BaseResponse<Void> handleEmptyResourceException(EmptyResourceException e, HttpServletRequest request) {

        log.error("Advice 내 HandleException 호출, {}, {}", request.getRequestURI(), e.getHttpStatusMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", Integer.toString(e.getHttpStatusCode()));
        map.put("message", e.getHttpStatusMessage());

        /* ResponseEntity의 생성장에는 HttpStatus 가 필요
         -> Custom HttpStatus를 사용할 경우 ResponseEntity 사용 불가
         -> return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus()); 불가능
         -> Custom ResponseEntity 사용이 필요하다.
         */

        return BaseResponse.error(e.getHttpStatus());
    }
}
