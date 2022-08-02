package com.example.blog.service;

import com.example.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends IService<Post> {
    Iterable<Post> findAllById( Long id);
    Iterable<Post> findAllByStatus();
    Iterable<Post> findAllByTitle(String title);
}

