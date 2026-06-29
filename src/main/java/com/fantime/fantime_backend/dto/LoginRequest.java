package com.fantime.fantime_backend.dto;

public record LoginRequest (
    String email,
    String password
) {
    
}
