package com.fantime.fantime_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private String groupName;

    private String title;

    private String content;
}
