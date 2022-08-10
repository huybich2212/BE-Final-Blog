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
    @GetMapping("/post/{postId}")
    public ResponseEntity<Iterable<Post_Label>> findAllByPostId(@PathVariable Long postId) {
        Iterable<Post_Label> post_Labels = post_LabelService.findAllByPostId(postId);
        return new ResponseEntity<>(post_Labels, HttpStatus.OK);
    }



    //create post-label
    @PostMapping("")
    public ResponseEntity<Post_Label> creatPostLabel(@RequestBody Post_Label post_Label) {
        Post_Label post_Label1 = post_LabelService.save(post_Label);
        return new ResponseEntity<>(post_Label1, HttpStatus.CREATED);
    }

    //delete post-label by post id and label id
    @DeleteMapping("")
    public ResponseEntity<Post_Label> deleteByPostIdAndLabelId(@RequestParam Long postId, @RequestParam Long labelId) {
        post_LabelService.removeByPostId(postId, labelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping("/count-by-label-id")
//    public ResponseEntity<Integer> countByLabelId(@RequestParam Long labelId){
//        return new ResponseEntity<>(post_LabelService.countPostByLabelID(labelId), HttpStatus.OK);
//    }
}
