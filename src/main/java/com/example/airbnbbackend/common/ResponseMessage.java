package com.example.airbnbbackend.common;

// postman 으로 요청에 대한 응답을 받을 때 에러 발생 여부를 응답 받기 위함.
// 즉 Exception + success
public interface ResponseMessage {
    String SUCCCESS = "Success!!";
    String VALIDATION_FAIL = "Validation fail!!";
    String DUPLICATE_ID = "Duplicate Id!!";

    String SIGN_IN_FAIL = "Sign Failed!!";
    String CERTIFICATION_FAIL = "Certification failed!!";
    String MAIL_FAIL = "Mail failed!!";
    String DATABASE_ERROR = "DB Error!!";
}
