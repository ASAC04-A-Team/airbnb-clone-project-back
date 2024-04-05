package com.example.airbnbbackend.common.exception.customException;

import com.example.airbnbbackend.common.Constants;
import lombok.Getter;
import org.springframework.http.HttpStatus;
public class NotFoundException extends RuntimeException {

    private Constants.ExceptionClass exceptionClass;

    @Getter
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(Constants.ExceptionClass exceptionClass, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return this.httpStatus.value();
    }

    public String getHttpStatusType() {
        return this.httpStatus.getReasonPhrase();
    }
}
