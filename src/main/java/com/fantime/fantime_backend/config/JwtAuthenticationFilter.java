package com.fantime.fantime_backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fantime.fantime_backend.security.JwtProvider;
import com.fantime.fantime_backend.entity.User;
import com.fantime.fantime_backend.repository.UserRepository;
import java.io.IOException;
import java.util.List;

@Component 
@RequiredArgsConstructor // final 로 선언된 필드를 생성자 주입

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization"); // 요청 헤더에서 authorization 값 가져오기

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // "Bearer " 제거하고 JWT 토큰만 
            Long userId = jwtProvider.getUserIdToken(token);

            User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
            String roleName = "ROLE_" + user.getRole();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName); 

            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(userId, null, List.of(authority));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); // 다음 필터로 보내기
    }
}
