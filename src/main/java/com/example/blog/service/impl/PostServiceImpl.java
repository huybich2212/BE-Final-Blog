package com.example.blog.service.impl;

import com.example.blog.model.Post;
import com.example.blog.model.Post_Label;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }



    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllById(Long id) {
        return postRepository.findAllById(id);
    }

    @Override
    public Iterable<Post> findAllByStatus() {
        return postRepository.findAllByStatus();
    }

    //FIND ALL POST BY USER ID AND TITLE
    public Iterable<Post> findAllByUserIdAndTitle(String title,Long userId) {
        return postRepository.findAllByTitleAndUserId(title,userId);
    }

    //find all by label id and user id
    public Iterable<Post> findAllByLabelIdAndUserId(Long labelId,Long userId) {
        return postRepository.findAllByLabelIdAndUserId(labelId,userId);
    }


}

