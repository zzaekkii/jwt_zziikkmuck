package com.example.yoomeejwt.service;

import com.example.yoomeejwt.domains.user.User;
import com.example.yoomeejwt.domains.user.UserRepositrory;
import com.example.yoomeejwt.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositrory userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new UserDetailsDto(user);
        }

        return null;
    }
}
