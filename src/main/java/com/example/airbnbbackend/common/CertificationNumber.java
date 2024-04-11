package com.example.airbnbbackend.common;

// 인증 코드 6자리
public class CertificationNumber {
    public static String getCertificationNumber() {
        String certificationNumber = "";
        for (int i = 0; i < 6; i++) {
            certificationNumber += (int) (Math.random() * 10);
        }

        return certificationNumber;
    }
}
