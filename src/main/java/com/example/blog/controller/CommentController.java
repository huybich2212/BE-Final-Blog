package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;
import com.example.blog.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    // get all comments

    @GetMapping("")
    public ResponseEntity<Iterable<Comment>> showAllComment() {
        Iterable<Comment> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // new comment
    @PostMapping("")
    public ResponseEntity<Comment>createComment(@RequestBody Comment comment){
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setCreateAt(localDateTime);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteCmt(@PathVariable Long id) {
        commentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //FIND ALL COMMENT BY POST ID
    //chỗ này hiện cmt k cần phân quyền user

    @GetMapping("/post/{id}")
    public ResponseEntity<Iterable<Comment>> findAllByPostId(@PathVariable Long id) {
        Iterable<Comment> comments = commentService.findAllByPostId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
