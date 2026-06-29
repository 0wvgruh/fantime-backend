package com.fantime.fantime_backend.dto;

public record LoginResponse ( // record - 데이터 담는 전용 클래스
    String token,
    String email,
    String nickname,
    String role
) {
    
}
