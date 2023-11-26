package com.example.hangdang.security;
/*
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.jwt.JwtUtil;
import com.example.hangdang.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
//@Component
//@Order(2)
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final UserRepository adminRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) && url.startsWith("/api/user")) {
            chain.doFilter(request, response);
        } else {
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);
            if (StringUtils.hasText(tokenValue)) {
                String token = jwtUtil.substringToken(tokenValue);
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Not Valid");
                }
                Claims info = jwtUtil.getAdminInfoFromToken(token);

                UserEntity user = userRepository.findByUsername(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("등록되지 않은 유저"));
                request.setAttribute("user", user);
                chain.doFilter(request, response);
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}
*/