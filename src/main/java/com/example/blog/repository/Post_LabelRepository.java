package com.example.blog.repository;

import com.example.blog.model.Post_Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface Post_LabelRepository extends JpaRepository<Post_Label, Long> {
    //find all label with post id
    Iterable<Post_Label> findAllByPostId(Long postId);

    //find all post with label id
    Iterable<Post_Label> findAllByLabelId(@PathVariable Long labelId);

    //delete post-label by post id and label id
    @Query(value = "delete from Post_Label p where p.postId = :postId and p.labelId = :labelId", nativeQuery = true)
    void deleteByPostIdAndLabelId(@Param("postId") Long postId, @Param("labelId") Long labelId);
}

