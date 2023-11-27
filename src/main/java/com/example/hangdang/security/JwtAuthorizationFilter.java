package com.example.hangdang.security;

import com.example.hangdang.global.dto.ApiResponse;
import com.example.hangdang.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;


    // JWT 검증 및 인가
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {


        String path = req.getRequestURI();
        if (path.equals("/") ||
                path.startsWith("/api/signup/check-name")
                || path.startsWith("/api/signup")
                || path.startsWith("/api/login")
                || path.startsWith("swagger-ui.html")) {
            filterChain.doFilter(req, res);
            return;
        }

        String tokenValue = jwtUtil.getJwtFromHeader(req);

        if (tokenValue == null || tokenValue.isEmpty()) {
            try {
                System.out.println("토큰이 비어있는 에러 발생");
                filterChain.doFilter(req, res);
                return;
            } catch (Exception e) {
                System.out.println("fail");
                return;
            }
        }

        if (StringUtils.hasText(tokenValue)) {
            System.out.println("토큰 인터널 체크");
            if (!jwtUtil.validateToken(tokenValue)) {
                log.error("Token Error");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(ApiResponse.error("토큰이 유효하지 않습니다."));

                res.setCharacterEncoding("UTF-8");
                res.setContentType("application/json");
                res.getWriter().write(jsonResponse);
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }

            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

            try {
                setAuthentication(info.getSubject());
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(req, res);

    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}