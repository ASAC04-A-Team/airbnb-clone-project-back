package com.example.airbnbbackend.provider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailProvider {
    private final JavaMailSender javaMailSender;

    // 메일 왔을 때 제목
    private final String AIRBNB = "[에어비앤비] 인증 메일입니다!!";

    public boolean sendCertificationMail(String email, String certificationNumber) {
        try {
            // 세션 객체를 인자로 받아 생성. (메일 서버와의 연결 설정 정보)
            // MimeMessage 객체를 사용하여 메일의 발신자, 수신자, 제목, 본문 등을 설정 가능
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            // 온 메일 내용
            String htmlContent = getCertificationMessage(certificationNumber);
            messageHelper.setTo(email);
            messageHelper.setSubject(AIRBNB);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 메일에 보낼 내용.
    private String getCertificationMessage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'> [에어비앤비] 인증 메일입니다!! </h1>";

        certificationMessage += "<h3 style='text-align: center;'> 인증 코드 : <strong style='font-size: 32px;'>" + certificationNumber + "</strong> </h3>";

        return certificationMessage;
    }
}
