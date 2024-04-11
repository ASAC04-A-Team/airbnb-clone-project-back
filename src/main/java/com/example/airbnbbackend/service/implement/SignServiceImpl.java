package com.example.airbnbbackend.service.implement;

import com.example.airbnbbackend.common.CertificationNumber;
import com.example.airbnbbackend.common.CommonResponse;
import com.example.airbnbbackend.domain.User;
import com.example.airbnbbackend.dto.requestDto.auth.CheckCertificationRequestDto;
import com.example.airbnbbackend.dto.requestDto.auth.EmailCertificationRequestDto;
import com.example.airbnbbackend.dto.responseDto.ResponseEmailDto;
import com.example.airbnbbackend.dto.responseDto.auth.CheckCertificationResponseDto;
import com.example.airbnbbackend.dto.responseDto.auth.EmailCertificationResponseDto;
import com.example.airbnbbackend.dto.responseDto.auth.SignInResultDto;
import com.example.airbnbbackend.dto.responseDto.auth.SignUpResultDto;
import com.example.airbnbbackend.provider.EmailProvider;
import com.example.airbnbbackend.provider.JwtProvider;
import com.example.airbnbbackend.repository.UserRepository;
import com.example.airbnbbackend.service.SignService;
import com.example.airbnbbackend.service.exception.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SignServiceImpl implements SignService {
    private final UserRepository userRepository;
    private final EmailProvider emailProvider;
    public PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            Long userId = dto.getId();
            String email = dto.getEmail();
            String pw = dto.getPw();
            String nickname = dto.getNickname();
            String nation = dto.getNation();
            String address = dto.getAddress();
            LocalDateTime registerAt = dto.getRegisterAt();
            Boolean isHost = dto.getIsHost();
            Boolean isAuth = dto.getIsAuth();
            String userToken = dto.getUserToken();

            boolean isExistedId = userRepository.existsUserById(userId);
            if(isExistedId) return EmailCertificationResponseDto.duplicatedId(); // 이미 존재하면 잡아주기

            String certificationNumber = CertificationNumber.getCertificationNumber();
            boolean isSucceed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSucceed) {
                return EmailCertificationResponseDto.mailSendFail();
            }

            User user = new User(userId, email, pw, nickname, nation, address, registerAt, isHost, isAuth, userToken);
            userRepository.save(user);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEmailDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    // CheckCertificationResponseDto 클래스 또는 그 부모 클래스의 인스턴스를 반환 타입으로 허용
    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {
            Long id = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            User user = userRepository.findUserById(id);

            // user 자체가 존재하지 않음. -> 인증을 보내지 않은 유저
            if (user == null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            // 유저의 인증 코드와 날린 인증 코드가 같은 지를 boolean
            boolean isMatched = user.getEmail().equals(email) && user.getEmailAuthCode().equals(certificationNumber);
            if(!isMatched) return CheckCertificationResponseDto.certificationFail();


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEmailDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public SignUpResultDto signUpResult(EmailCertificationRequestDto dto) {
        log.info("[SignUpResult] 회원 가입 정보 전달");
        User user;
        if (dto.getRole().equalsIgnoreCase("admin")) {
            user = User.builder()
                    .email(dto.getEmail())
                    .nickname(dto.getNickname())
                    .pw(passwordEncoder.encode(dto.getPw()))
                    .address(dto.getAddress())
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else{
            user = User.builder()
                    .email(dto.getEmail())
                    .nickname(dto.getNickname())
                    .pw(passwordEncoder.encode(dto.getPw()))
                    .address(dto.getAddress())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User saveUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        log.info("[SignUpResult] 회원 가입 정보 끝 이제 결과값 주입");

        if (!saveUser.getNickname().isEmpty()) {
            log.info("정상 처리 완료");

            setSuccessResult(signUpResultDto);
        } else {
            log.info("실패 처리 완료");
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    @Override
    public SignInResultDto signInResult(String email, String password) throws RuntimeException {
        log.info("[SignInResult] 회원 정보 요청");
        User user = userRepository.getUserByEmail(email);
        log.info("email : {}", email);

        log.info("패스워드 비교");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        log.info("패스워드 일치");

        log.info("SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .userToken(jwtProvider
                        .createToken(String.valueOf(user.getEmail()), user.getRoles()))
                .build();

        log.info("객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    @Override
    public User signUp(String email, String password, String nickname, String nation, String address, LocalDateTime registerAt, String email_auth_code, boolean isHost, boolean isAuth, String userToken) throws UserDuplicateException {
        userRepository.findUserByEmail(email)
                .ifPresent(m->{
                    throw new UserDuplicateException("이미 존재하는 아이디 입니다.");
                });
        log.info("dto.getPw() : {}", password);
        log.info("dto.getAddress() : {}", address);
        log.info("dto.getIsHost() : {}", isHost);
        log.info("dto.getIsAuth() : {}", isAuth);
        log.info("dto.getRegisterAt() : {}", registerAt);

        return userRepository.save(User.builder()
                .email(email)
                .pw(passwordEncoder.encode(password))
//                .pw(password)
                .nickname(nickname)
                .nation(nation)
                .address(address)
                .registerAt(registerAt)
                .emailAuthCode(email_auth_code)
                .isHost(isHost)
                .isAuth(isAuth)
                .roles(Collections.singletonList("ROLE_USER"))
                .userToken(userToken)
                .build());
    }

    @Override
    public User login(String email, String password) throws UsernameNotFoundException, BadCredentialsException, Throwable {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 아이디입니다."));
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("잘못된 비밀번호입니다.");
        }
        return user;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMessage(CommonResponse.FAIL.getMessage());
    }
}
