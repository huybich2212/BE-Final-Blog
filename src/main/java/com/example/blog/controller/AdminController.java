package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.impl.PostServiceImpl;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> showAllPost() {
        Iterable<Post> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    // delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postOptional.get().setStatus(0);
        postService.remove(postOptional.get().getId());
        return new ResponseEntity<>(postOptional.get(), HttpStatus.NO_CONTENT);

    }

    //find all post by title
    @GetMapping("/title")
    public ResponseEntity<Iterable<Post>> findAllByTitle(@RequestParam String title) {
        Iterable<Post> posts = postService.findByTitle(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //find all post by content
    @GetMapping("/content")
    public ResponseEntity<Iterable<Post>> findAllByContent(@RequestParam String content) {
        Iterable<Post> posts = postService.findByContent(content);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //find all user
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> findAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //find all user by username
        @GetMapping("/users/{username}")
    public ResponseEntity<Iterable<User>> findAllByUsername(@PathVariable String username) {
        Iterable<User> users = userService.findAllByUsernameContaining(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //set enabled to false
    @PutMapping("/users/{id}")
    public ResponseEntity<User> setEnabledToFalse(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userOptional.get().setEnabled(false);
        userService.save(userOptional.get());
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    //set enabled to true
    @PutMapping("/users/{id}/enabled")
    public ResponseEntity<User> setEnabledToTrue(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userOptional.get().setEnabled(true);
        userService.save(userOptional.get());
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

}
