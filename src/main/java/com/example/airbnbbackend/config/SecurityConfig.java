package com.example.airbnbbackend.config;

import com.example.airbnbbackend.filter.JwtAuthenticationFilter;
import com.example.airbnbbackend.handler.CustomAccessDeniedHandler;
import com.example.airbnbbackend.handler.CustomAuthenticationFailHandler;
import com.example.airbnbbackend.handler.CustomAuthenticationSuccessHandler;
import com.example.airbnbbackend.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailHandler customAuthenticationFailHandler;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
//                .cors(cors -> cors
//                        .configurationSource(corsConfigurationSource())
//                )
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )


                .authorizeHttpRequests()
                .requestMatchers("/api/users/signin", "/api/users/signup", "/exception").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
//                .requestMatchers(HttpMethod.POST).permitAll()
                .requestMatchers("**exception**").permitAll();
//                .anyRequest().hasRole("ADMIN");

        httpSecurity
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);


//        httpSecurity
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
//                .anyRequest().authenticated()
//
//                .and()
//                .formLogin()
//                .loginPage("/api/users/") // 로그인 페이지
//                .loginProcessingUrl("/api/users/login") // 실제 로그인이 되는 url -> 로그인 데이터를 처리할 URL을 지정. 즉 ID, 비번 제출할 URL
//                .permitAll()
//                .successHandler(customAuthenticationSuccessHandler)
//                .failureHandler(customAuthenticationFailHandler)
//                .and()
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/")
//                );

        //CSRF 토큰 사용한다면 이렇게 하지만 JWT를 사용하므로 CSRF 끔
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        return httpSecurity.build();
    }


    // CORS 설정
    /*@Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 모든 출처 Origin, Method, Header 다 허용 -> 다른 출처의 리소스에 자유롭게 접근하도록 함.
        corsConfiguration.addAllowedOrigin("*"); // 교차 출처 요청이 허용
        corsConfiguration.addAllowedMethod("*"); // 허용할 HTTP 메소드를 설정
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 수정

        return source;
    }*/

    /*@Bean
    public void configure(WebSecurity webSecurity) {
        // 이후 스웨거 관련해서 추가
        webSecurity.ignoring().requestMatchers("/swagger/**", "/swagger-resources/**");
    }*/

}
