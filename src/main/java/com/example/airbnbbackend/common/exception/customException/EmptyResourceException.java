package com.example.airbnbbackend.common.exception.customException;

import com.example.airbnbbackend.common.Constants;
import com.example.airbnbbackend.common.BaseResponseStatus;
import lombok.Getter;

public class EmptyResourceException extends RuntimeException {

    // Exception 발생 Domain
    private Constants.ExceptionClass exceptionClass;

    @Getter
    private final BaseResponseStatus httpStatus = BaseResponseStatus.EMPTY_RESOURCE;

    public EmptyResourceException(Constants.ExceptionClass exceptionClass, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }
    public int getHttpStatusCode() {
        return this.httpStatus.getCode();
    }
    public String getHttpStatusMessage() {
        return this.httpStatus.getMessage();
    }
}
