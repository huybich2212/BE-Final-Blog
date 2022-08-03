package com.example.blog.service.impl;

import com.example.blog.model.Likes;
import com.example.blog.model.Post_Label;
import com.example.blog.repository.LikesRepository;
import com.example.blog.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Override
    public Optional<Likes> findById(Long id) {
        return likesRepository.findById(id);
    }

    @Override
    public Iterable<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public Post_Label save(Likes likes) {
        likesRepository.save(likes);
        return null;
    }

    @Override
    public void remove(Long id) {
        likesRepository.deleteById(id);
    }
}

