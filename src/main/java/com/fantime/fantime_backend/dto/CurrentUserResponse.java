package com.fantime.fantime_backend.dto;

public record CurrentUserResponse(
    Long id,
    String email,
    String nickname,
    String role
) {
}