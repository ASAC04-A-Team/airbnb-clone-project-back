package com.example.airbnbbackend.common;

import lombok.Getter;

public class Constants {
    // 상수 통합 관리 클래스

    /**
     * 커스텀 예외 클래스에서 어떤 도메인에서 문제가 발생했는 지 보여주는 용도
     */
    public enum ExceptionClass {

        USER("User"),
        ROOM("Room"),
        ROOM_CATEGORY("RoomCategory");

        @Getter
        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Domain Exception. ";
        }
    }
}
