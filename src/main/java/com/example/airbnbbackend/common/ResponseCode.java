package com.example.airbnbbackend.common;

// postman 으로 요청에 대한 응답을 받을 때 에러 발생 여부를 응답 받기 위함.
// 즉 Exception + success
public interface ResponseCode {
    String SUCCCESS = "US";
    String VALIDATION_FAIL = "VF";
    String DUPLICATE_ID = "DI";
    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";
    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";
}
