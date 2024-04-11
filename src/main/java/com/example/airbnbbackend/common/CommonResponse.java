package com.example.airbnbbackend.common;

import lombok.Getter;

@Getter
public enum CommonResponse {
    SUCCESS(0, "Succcess"), FAIL(-1, "Fail");

    final int code;
    final String message;


    CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
