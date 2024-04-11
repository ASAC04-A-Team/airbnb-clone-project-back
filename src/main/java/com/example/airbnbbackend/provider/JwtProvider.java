package com.example.airbnbbackend.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret-key}")
    private String secretKey = "secretKey"; // ?
//    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long tokenValidMillisecond = 1000L * 60 * 60;

    @PostConstruct
    protected void init() {
        log.info("[init] JwtTokenProvider secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
//        secretKey = Encoders.BASE64.encode(key.getEncoded());
//        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider secretKey = {}", secretKey);
        log.info("[init] JwtTokenProvider secretKey 초기화 완료");
    }
//    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); 이거는 뭔지

    // 토큰 생성하기
    public String createToken(String email, List<String> roles) {
        log.info("[토큰 생성 시작]");
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);

        // 토큰 유효 기간 1시간
        Date now = new Date();
//        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .compact();

        log.info("[토큰 생성 완료]");
        // 토큰 생성 완료
        return jwt;
    }

    public String getUsername(String userToken) {
        log.info("토큰 기반 회원 구별 정보 추출");
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(userToken).getBody().getSubject();
        log.info("토큰 기반 회원 구별 정보 추출 완료 = info :  {}", info);
        return info;
    }

    public String resolveToken(HttpServletRequest request) {
        log.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        log.info("[resolveToken] HTTP 헤더에서 Token 값 추출 : {}", request.getHeader("Authorization"));

        return request.getHeader("Authorization");
    }

    public Authentication getAuthentication(String userToken) {
        log.info("토큰 인증 정보 조회 시작 1");

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(userToken));
        log.info("토큰 인증 정보 조회 끝 = UserDetails {}", userDetails);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String userToken) {
        log.info("[validateToken] : 토큰 유효한지 체크");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(userToken);
            log.info("validate = {}" ,claims);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
//            log.warn(e.getMessage());
            log.warn(e.toString());
            log.warn("[validateToken] : 토큰 유효 체크 예외 발생");
            return false;
        }
    }
}
