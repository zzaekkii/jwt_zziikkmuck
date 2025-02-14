package com.example.yoomeejwt.controller;

import com.example.yoomeejwt.dto.SignupDto;
import com.example.yoomeejwt.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public String signupProcess(SignupDto signupDto) {

        log.info("signup username: {}", signupDto.getUsername());
        signupService.signupProcess(signupDto);

        return "signup success";
    }
}
