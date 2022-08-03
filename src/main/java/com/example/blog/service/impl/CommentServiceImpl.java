package com.example.blog.service.impl;

import com.example.blog.model.Comment;
import com.example.blog.model.Post_Label;
import com.example.blog.repository.CommentRepository;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    public Iterable<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Post_Label save(Comment comment) {
        commentRepository.save(comment);
        return null;
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    //find all comment by post id
    public Iterable<Comment> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}

