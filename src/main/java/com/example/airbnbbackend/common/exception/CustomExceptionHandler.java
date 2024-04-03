package com.example.airbnbbackend.common.exception;

import com.example.airbnbbackend.common.exception.customException.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();

        log.error("Advice 내 HandleException 호출, {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("code", Integer.toString(e.getHttpStatusCode()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
    }
}
