package com.example.airbnbbackend.dto.responseDto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResultDto extends SignUpResultDto{
    private String userToken;

    @Builder
    public SignInResultDto(boolean success, int code, String message, String userToken) {
        super(success, code, message);
        this.userToken = userToken;
    }
}
