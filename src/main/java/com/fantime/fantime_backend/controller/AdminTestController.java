package com.fantime.fantime_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminTestController { // security 권한 분리 테스트 컨트롤러
    @GetMapping("/api/admin/test") 
    public String adminTest() {
        return "관리자 접근 성공";
    }
}
