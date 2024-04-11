package com.example.airbnbbackend.dto.responseDto.auth;

import com.example.airbnbbackend.dto.responseDto.ResponseEmailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CheckCertificationResponseDto extends ResponseEmailDto {
    private CheckCertificationResponseDto() {
        super();
    }


    public static ResponseEntity<CheckCertificationResponseDto> success() {
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // 실패
    public static ResponseEntity<ResponseEmailDto> certificationFail() {
        ResponseEmailDto responseBody = new ResponseEmailDto();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
