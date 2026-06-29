package com.fantime.fantime_backend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fantime.fantime_backend.dto.PostCreateRequest;
import com.fantime.fantime_backend.dto.PostResponse;
import com.fantime.fantime_backend.dto.PostUpdateRequest;
import com.fantime.fantime_backend.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;
    
    @PostMapping
    public PostResponse createPost( // 이 메서드 실행 결과를 PostResponse 로 돌려줌
        @RequestBody PostCreateRequest request,
        Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();

        return postService.createPost(userId, request);
    }

    @GetMapping  
    public List<PostResponse> getPosts(
        @RequestParam(required = false) String groupName
    ) {
        return postService.getPosts(groupName);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(
        @PathVariable Long postId // 주소 안에 있는 값 변수로 꺼내기
    ) {
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}") 
    public PostResponse updatePost(
        @PathVariable Long postId,
        @RequestBody PostUpdateRequest request,
        Authentication anthentication
    ) {
        Long userId = (Long) anthentication.getPrincipal();

        return postService.updatePost(userId, postId, request);
    }
}
