package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // get all comments

    @GetMapping("/all")
    public ResponseEntity<Iterable<Comment>> showAllComment() {
        Iterable<Comment> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // new comment
    @PostMapping("/add")
    public ResponseEntity<Comment>createComment(@RequestBody Comment comment){
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    //update comment
    @PutMapping("/update/{id}")
    public ResponseEntity<Comment>updateCmt(@PathVariable Long id,@RequestBody Comment comment) {
        Optional<Comment> comment1 = commentService.findById(id);
        if (!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(comment1.get().getId());
        commentService.save(comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }

    //delete comment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteCmt(@PathVariable Long id) {
        commentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
