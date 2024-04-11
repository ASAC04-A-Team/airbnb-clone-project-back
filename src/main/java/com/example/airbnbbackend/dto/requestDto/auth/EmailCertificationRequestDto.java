package com.example.airbnbbackend.dto.requestDto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EmailCertificationRequestDto {
    @NotNull
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String pw;

    @NotBlank
    private String nickname;

    @NotBlank
    private String nation;

    @NotBlank
    private String address;

    private String emailAuthCode;

    @NotNull
    private LocalDateTime registerAt;

    @NotNull
    private Boolean isHost;

    @NotNull
    private Boolean isAuth;

    private String userToken;

    @NotBlank
    private String role;
}
