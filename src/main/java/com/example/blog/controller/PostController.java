package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> showAllPost() {
        Iterable<Post> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        LocalDateTime now = LocalDateTime.now();
        post.setCreateAt(now);
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> post1 = postService.findById(id);
        if (!post1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(id);
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Post>> findAllById(@PathVariable(value = "id") Long id) {
        Iterable<Post> posts = postService.findAllById(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id){
        postService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>>findById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<Iterable<Post>>findAllByStatus() {
        Iterable<Post> posts = postService.findAllByStatus();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
}
