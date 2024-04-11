package com.example.airbnbbackend.dto.responseDto.auth;

import com.example.airbnbbackend.common.ResponseCode;
import com.example.airbnbbackend.common.ResponseMessage;
import com.example.airbnbbackend.dto.responseDto.ResponseEmailDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class EmailCertificationResponseDto extends ResponseEmailDto {
    private EmailCertificationResponseDto() {
        super();
    }

    public static ResponseEntity<EmailCertificationResponseDto> success() {
        EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseEmailDto> duplicatedId() {
        ResponseEmailDto responseBody = new ResponseEmailDto(ResponseCode.DUPLICATE_ID, ResponseCode.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseEmailDto> mailSendFail() {
        ResponseEmailDto responseBody = new ResponseEmailDto(ResponseCode.MAIL_FAIL, ResponseMessage.MAIL_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
