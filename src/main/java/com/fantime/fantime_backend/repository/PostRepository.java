package com.fantime.fantime_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fantime.fantime_backend.entity.Post;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByGroupName(String groupName);
}


