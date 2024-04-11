package com.example.airbnbbackend.dto.requestDto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
    @NotNull
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
