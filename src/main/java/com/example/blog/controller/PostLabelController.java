package com.example.blog.controller;

import com.example.blog.model.Post_Label;
import com.example.blog.service.impl.Post_LabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post-labels")
public class PostLabelController {

    @Autowired
    private Post_LabelServiceImpl post_LabelService;
    //find all post-label with post id
    @GetMapping("/{postId}")
    public ResponseEntity<Iterable<Post_Label>> findAllByPostId(@PathVariable Long postId) {
        Iterable<Post_Label> post_Labels = post_LabelService.findAllByPostId(postId);
        return new ResponseEntity<>(post_Labels, HttpStatus.OK);
    }
}
