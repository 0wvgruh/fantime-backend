package com.fantime.fantime_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    private String groupName;

    private String title;

    private String content;
}
