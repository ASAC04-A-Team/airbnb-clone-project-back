package com.example.airbnbbackend.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {
    @NotBlank(message = "회원 이름은 필수 항목입니다.")
    private String nickname;

    private LocalDateTime birthDay;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @NotBlank(message = "인증 코드는 필수 항목입니다.")
    private String emailAuthCode;

//    public static UserForm createMemberForm(User member){
//        UserForm form = new UserForm();
//        form.loginId = member.getLoginId();
//        form.password = member.getPassword();
//        form.username = member.getUsername();
//        form.city = member.getAddress().getCity();
//        form.street = member.getAddress().getStreet();
//        form.detail = member.getAddress().getDetail();
//        return form;
//    }
}
