package com.example.yoomeejwt.jwt;

import com.example.yoomeejwt.domains.user.AccountRole;
import com.example.yoomeejwt.domains.user.User;
import com.example.yoomeejwt.dto.UserDetailsDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization= request.getHeader("Authorization");

        // Authorization 헤더 검증.
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            return;
        }

        // Bearer 뒤에 있는 토큰 값.
        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {

            filterChain.doFilter(request, response);

            return;
        }

        User user = User.builder()
                .username(jwtUtil.getUsername(token))
                .password("temp")
                .role(AccountRole.valueOf(jwtUtil.getRole(token)))
                .build();

        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetailsDto, null, userDetailsDto.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
