package com.example.blog.service;

import com.example.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends IService<Post> {
    Page<Post> findAllById( Pageable pageable);
    Page<Post> findAllByStatus(Pageable pageable);
}

