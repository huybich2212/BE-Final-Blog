package com.example.blog.service.impl;

import com.example.blog.model.Post_Label;
import com.example.blog.repository.Post_LabelRps;
import com.example.blog.service.Post_LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class Post_LabelServiceImpl implements Post_LabelService {

    @Autowired
    private Post_LabelRps post_LabelRps;

    public Iterable<Post_Label> findAll(Pageable pageable) {
        return post_LabelRps.findAll(pageable);
    }

    @Override
    public Optional<Post_Label> findById(Long id) {
        return post_LabelRps.findById(id);
    }

    @Override
    public Iterable<Post_Label> findAll() {
        return post_LabelRps.findAll();
    }

    @Override
    public void save(Post_Label post_label) {
        post_LabelRps.save(post_label);
    }

    @Override
    public void remove(Long id) {
        post_LabelRps.deleteById(id);
    }
}
