package com.example.yoomeejwt.service;

import com.example.yoomeejwt.domains.user.User;
import com.example.yoomeejwt.domains.user.UserRepositrory;
import com.example.yoomeejwt.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.yoomeejwt.domains.user.AccountRole.USER;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepositrory userRepositrory;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signupProcess(SignupDto signupDto) {

        // username 중복 검사 - 도메인 서비스로 분리하고 싶긴한데 일단 보류.
        if (userRepositrory.existsByUsername(signupDto.getUsername())) {
            return;
        }

        User newUser =
                User.builder()
                        .username(signupDto.getUsername())
                        .password(passwordEncoder.encode(signupDto.getPassword()))
                        .role(USER)
                        .build();

        userRepositrory.save(newUser);
    }
}
