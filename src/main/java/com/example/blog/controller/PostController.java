package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.model.Likes;
import com.example.blog.model.Post;
import com.example.blog.service.LikesService;
import com.example.blog.service.PostService;
import com.example.blog.service.Post_LabelService;
import com.example.blog.service.impl.PostServiceImpl;
import com.example.blog.service.impl.Post_LabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private LikesService likesService;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private Post_LabelServiceImpl post_LabelService;

    // find all post by status public
    // để bên guest
//    @GetMapping("")
//    public ResponseEntity<Iterable<Post>> showAllPost() {
//        Iterable<Post> posts = postService.findAllByStatus();
//        return new ResponseEntity<>(posts,HttpStatus.OK);
//    }

    // new post
    @PostMapping("")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        LocalDateTime now = LocalDateTime.now();
        post.setCreateAt(now);
        postService.save(post);

        //tạo post-label theo post id
//        Post_Label post_Label = {
//            post.getId(),
//            post.getLabelId()
//        };
//        post_LabelService.save(post_Label);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    // get post by user id
    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Post>> findAllById(@PathVariable(value = "id") Long id) {
        Iterable<Post> posts = postService.findAllById(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get post by id
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Post>>findById(@PathVariable Long id) {
//        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
//    }

    //find post by title and status public
    @GetMapping("/title")
    public ResponseEntity<Iterable<Post>>findAllByTitle(@RequestParam String title) {
        Iterable<Post> posts = postService.findByTitleContaining(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    //FIND ALL POST BY USER ID AND TITLE
    @GetMapping("/find-by-title-of-user")
    public ResponseEntity<Iterable<Post>> findAllByUserIdAndTitle(@RequestParam(value = "userid") Long id, @RequestParam(value = "title") String title) {
        Iterable<Post> posts = postService.findAllByUserIdAndTitle(title,id );
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //find all post with label id and user id
    @GetMapping("/label/{id}/user/{userId}")
    public ResponseEntity<Iterable<Post>> findAllByLabelIdAndUserId(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        Iterable<Post> posts = postService.findAllByLabelIdAndUserId(id,userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Post>editPost(@PathVariable Long id,@RequestBody Post post) {
        Optional<Post> post1 = postService.findById(id);
        if (!post1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(post1.get().getId());
        postService.save(post);
        return new ResponseEntity<>(post,HttpStatus.CREATED);
    }

    //find post with number of like
    @GetMapping("/like")
    public ResponseEntity<Iterable<Post>> findAllByLike() {
        Iterable<Post> posts = postService.findByNumberOfLike();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //delete post by id and user id
//    @DeleteMapping("/{id}/{userId}")
//    public ResponseEntity<Post>deleteByIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
//        Optional<Post> post = postService.findById(id);
//        if (!post.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        postService.deleteByIdAndUserId(id,userId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id){
        postService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //find all post by user id and status public
    @GetMapping("/public/user/{id}")
    public ResponseEntity<Iterable<Post>> findAllByUserIdAndStatusPublic(@PathVariable(value = "id") Long id) {
        Iterable<Post> posts = postService.findPostStatusAndAllOfUser(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PutMapping("/update-like-by-post-id/{id}")
    public ResponseEntity<Post>updateLike(@PathVariable Long id,@RequestBody Post post ,  Likes like) {
        Optional<Post> post12 = postService.findById(id);
        if (!post12.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(post12.get().getId());
        post.setNumberOfLike(postService.countLikes(id));
        postService.save(post);
        return new ResponseEntity<>(post,HttpStatus.CREATED);
    }

//    @PostMapping("/like-post-by-postId/{id}")
//    public ResponseEntity<Likes> createLikes(@RequestBody Likes like , Post post) {
//        LocalDateTime now = LocalDateTime.now();
//        like.setDateTime(now);
//        post.setNumberOfLike(post.getNumberOfLike()+1);
//        likesService.save(like);
//        return new ResponseEntity<>(like, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/unlike-from-postId{id}")
//    public ResponseEntity<Likes> unlike(@PathVariable Long id, Post post) {
//        likesService.remove(id);
//        post.setNumberOfLike(post.getNumberOfLike()-1);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
