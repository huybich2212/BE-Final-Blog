package com.example.blog.controller;

import com.example.blog.model.Likes;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.LikesService;
import com.example.blog.service.impl.LikesServiceImpl;
import com.example.blog.service.impl.PostServiceImpl;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikesServiceImpl likesService;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

//    @GetMapping("")
//    public ResponseEntity<Iterable<Likes>> showAllLikes() {
//        Iterable<Likes> likes = likesService.findAll();
//        return new ResponseEntity<>(likes,HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Likes>>findById(@PathVariable Long id) {
//        return new ResponseEntity<>(likesService.findById(id),HttpStatus.OK);
//    }
    private boolean checkLike(User user, Post post, Iterable<Likes> likePostUses) {
        for (Likes i : likePostUses) {
            if (i.getPost() == post && i.getUser() == user && i.getId()==null) {
                return false;
            }
        }
        return true;
    }
    @GetMapping ("")
    public ResponseEntity<Likes> likedCheck(@RequestParam Long postId,@RequestParam Long userId){
//        Likes likes = new Likes();
        Post post = postService.findById(postId).get();
        User user = userService.findById(userId).get();
        Likes like = likesService.findLikeByUserIdAndPostId(user.getId(), post.getId());
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Likes> Like(@RequestParam Long postId,@RequestParam Long userId) {
        Likes likes = null;
        Post post = postService.findById(postId).get();
        User user = userService.findById(userId).get();
        Likes likedUserPost = likesService.findLikeByUserIdAndPostId(user.getId(), post.getId());
        if (checkLike(user, post, likesService.findAll())) {
             likes = new Likes();
            if (likedUserPost == null) {
                likes.setUser(user);
                likes.setPost(post);
                LocalDateTime now = LocalDateTime.now();
                likes.setDateTime(now);
                likesService.save(likes);
                int newLikes = post.getNumberOfLike();
                post.setNumberOfLike(newLikes + 1);
                postService.save(post);
            } else {
                likesService.remove(likedUserPost.getId());
                int oldLikes = post.getNumberOfLike();
                post.setNumberOfLike(oldLikes - 1);
                postService.save(post);
            }
        }
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
//    @PostMapping("")
//    public ResponseEntity<Post> like(@RequestParam Long postId,@RequestParam Long userId) {
//        Likes likes = new Likes();
//        Post post = postService.findById(postId).get();
//        User user = userService.findById(userId).get();
//        Likes likedUserPost = likesService.findLikeByUserIdAndPostId(user.getId(), post.getId());
//        likes.setUser(user);
//        likes.setPost(post);
//        likesService.save(likedUserPost);
//        return new ResponseEntity<>(post, HttpStatus.OK);
//    }

}

//    @PostMapping("")
//    public ResponseEntity<LikeStatus> likeStatus(@RequestParam Long idStatus, @RequestParam Long idUser) {
//        LikeStatus likeStatus = new LikeStatus();
//        Status status = statusService.findById(idStatus).get();
//        User userOptional = userService.findById(idUser).get();
//        LikeStatus likeStatuses = likeStatusService.findByUserLikeIdAndAndStatusId(userOptional.getId(), status.getId());
//        if (checkLikeStatus(userOptional, status, likeStatusService.findAll())) {
//            if (likeStatuses == null) {
//                likeStatus.setUserLike(userOptional);
//                likeStatus.setStatus(status);
//                likeStatusService.save(likeStatus);
//            } else {
//                likeStatusService.delete(likeStatuses.getId());
//            }
//        }
//        return new ResponseEntity<>(likeStatuses, HttpStatus.OK);
//    }

//    @DeleteMapping("/unlike")
//    public ResponseEntity<Likes> unlike(@RequestBody Likes likes) {
//        Post post =postService.findById(likes.getPost().getId()).get();
//        User user =userService.findById(likes.getUser().getId()).get();
//        Likes likesId =likesService.findLikeByUserIdAndPostId(user.getId(), post.getId());
//        Integer oldLikes=post.getNumberOfLike();
//        oldLikes = oldLikes == null ? Integer.valueOf(0) : oldLikes;
//        post.setNumberOfLike(oldLikes-Integer.valueOf(1));
//        postService.save(post);
//        likesService.save(likesId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
