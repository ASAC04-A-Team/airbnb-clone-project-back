package com.example.airbnbbackend.service.implement;

import com.example.airbnbbackend.domain.User;
import com.example.airbnbbackend.dto.responseDto.auth.EmailCertificationResponseDto;
import com.example.airbnbbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUser = {} loadUserByUsername 실행", username);


        User user = userRepository.findByEmail(username);
        /*if (user == null) {
            user = userRepository.save(new User());
        }*/
        log.info("user 상태: {} {}", username, user.getId());
        log.info(user.toString());
        return user;
    }
}