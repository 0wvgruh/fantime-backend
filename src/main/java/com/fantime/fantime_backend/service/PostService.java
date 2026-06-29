package com.fantime.fantime_backend.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fantime.fantime_backend.dto.PostCreateRequest;
import com.fantime.fantime_backend.dto.PostResponse;
import com.fantime.fantime_backend.dto.PostUpdateRequest;
import com.fantime.fantime_backend.entity.Post;
import com.fantime.fantime_backend.entity.User;
import com.fantime.fantime_backend.repository.PostRepository;
import com.fantime.fantime_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponse createPost(Long userId, PostCreateRequest request) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Post post = new Post();
        post.setGroupName(request.getGroupName());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(user);

        Post savedPost = postRepository.save(post); // DB 저장

        PostResponse response = new PostResponse();
        response.setId(savedPost.getId());
        response.setGroupName(savedPost.getGroupName());
        response.setTitle(savedPost.getTitle());
        response.setContent(savedPost.getContent());
        response.setAuthorNickname(savedPost.getAuthor().getNickname());
        response.setCreatedAt(savedPost.getCreatedAt());
        response.setUpdatedAt(savedPost.getUpdatedAt());

        return response;
    } 

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts(String groupName){ // postResponse 여러개 반환
        List<Post> posts; // post 여러개 담을 수 잇음

        if (groupName == null || groupName.isBlank()){
            posts = postRepository.findAll();
        } else {
            posts = postRepository.findByGroupName(groupName);
        }

        return posts.stream()
            .map(this::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId){
        Post post = postRepository.findById(postId) 
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        return toResponse(post);
    }

    public PostResponse updatePost(Long userId, Long postId, PostUpdateRequest request){ // userId, postId, request = 재료
        Post post = postRepository.findById(postId) 
            .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        if (!post.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("게시글 수정 권한이 없습니다.");
        }
        post.setGroupName(request.getGroupName());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        Post updatedPost = postRepository.save(post); // 수정된 post를 DB에 저장하고, 저장된 결과를 updatedPost에 담음

        return toResponse(updatedPost);
    }

    private PostResponse toResponse(Post post) { // DB에서 조회된 게시글 하나가 post 객체로 들어옴
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setGroupName(post.getGroupName());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setAuthorNickname(post.getAuthor().getNickname());
        response.setCreatedAt(post.getCreatedAt());
        response.setUpdatedAt(post.getUpdatedAt());

        return response;
    }
}
