package com.fantime.fantime_backend.security;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}") // 서명용 비밀키
    private String jwtSecret;

    @Value("${jwt.expiration}") // 만료 시간
    private Long jwtExpiration;

    public String createToken(Long userId, String email, String role) { // 로그인 성공 유저 정보 받음
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder() // 토큰 생성
            .subject(String.valueOf(userId)) // userId를 subject로 설정
            .claim("email", email)
            .claim("role", role)
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }

    public Long getUserIdToken(String token){
        return Long.parseLong(Jwts.parser() // 토큰 읽기
            .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject());
    }
}