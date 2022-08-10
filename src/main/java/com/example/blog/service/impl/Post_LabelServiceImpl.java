package com.example.blog.service.impl;

import com.example.blog.model.Post_Label;
import com.example.blog.repository.Post_LabelRepository;
import com.example.blog.service.Post_LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class Post_LabelServiceImpl implements Post_LabelService {

    @Autowired
    private Post_LabelRepository post_LabelRepository;

    public Iterable<Post_Label> findAll(Pageable pageable) {
        return post_LabelRepository.findAll(pageable);
    }

    @Override
    public Optional<Post_Label> findById(Long id) {
        return post_LabelRepository.findById(id);
    }

    @Override
    public Iterable<Post_Label> findAll() {
        return post_LabelRepository.findAll();
    }

    @Override
    public Post_Label save(Post_Label post_label) {
        post_LabelRepository.save(post_label);
        return post_label;
    }

    @Override
    public void remove(Long id) {
        post_LabelRepository.deleteById(id);
    }

    //find all by post id
    public Iterable<Post_Label> findAllByPostId(Long postId) {
        return post_LabelRepository.findAllByPostId(postId);
    }

    //find all post with label id
    public Iterable<Post_Label> findAllByLabelId(Long labelId) {
        return post_LabelRepository.findAllByLabelId(labelId);
    }

    //remove post-label
    public void removeByPostId(Long postId, Long labelId) {
        post_LabelRepository.deleteByPostIdAndLabelId(postId, labelId);
    }
    //get list label and post per label
    public Iterable<String> getListLabelAndNumberPost() {
        return post_LabelRepository.getListLabelAndNumberOfPostPerLabel();
    }
    public Iterable<Post_Label>getPostLabelsByPostId(Long postId){
        return post_LabelRepository.findPost_LabelByPostId(postId);
    }

}
