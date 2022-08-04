package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/guest")
public class GuestController {
    @Autowired
    private PostServiceImpl postService;

    // find all post by status public
    @GetMapping("")
    public ResponseEntity<Iterable<Post>> showAllPost() {
        Iterable<Post> posts = postService.findAllByStatus();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>>findById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
    }

    //find post by title and status public

    @GetMapping("/title")
    public ResponseEntity<Iterable<Post>>findAllByTitle(@RequestParam String title) {
        Iterable<Post> posts = postService.findByTitleContaining(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
