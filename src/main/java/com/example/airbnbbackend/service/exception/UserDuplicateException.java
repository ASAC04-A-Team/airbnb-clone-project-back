package com.example.airbnbbackend.service.exception;

public class UserDuplicateException extends RuntimeException{

    public UserDuplicateException(String message) {
        super(message);
    }
}
