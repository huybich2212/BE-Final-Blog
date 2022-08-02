package com.example.blog.repository;

import com.example.blog.model.Post_Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Post_LabelRepository extends JpaRepository<Post_Label, Long> {
    //find all label with post id
    Iterable<Post_Label> findAllByPostId(Long postId);

    //find all post with label id and user id
    Iterable<Post_Label> findAllByLabelIdAndUserId(Long labelId, Long userId);

}

