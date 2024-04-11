package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.common.ResponseCode;
import com.example.airbnbbackend.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseEmailDto {
    private String code;
    private String message;

    public ResponseEmailDto() {
        this.code = ResponseCode.SUCCCESS;
        this.message = ResponseMessage.SUCCCESS;
    }

    public static ResponseEntity<ResponseEmailDto> databaseError() {
        ResponseEmailDto body = new ResponseEmailDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    public static ResponseEntity<ResponseEmailDto> validationFail() {
        ResponseEmailDto body = new ResponseEmailDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
