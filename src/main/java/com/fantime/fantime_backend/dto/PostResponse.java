package com.fantime.fantime_backend.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private Long id;

    private String groupName;

    private String title;

    private String content;

    private String authorNickname;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
