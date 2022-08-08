package com.example.blog.repository;

import com.example.blog.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query(value = "select COUNT(*) from likes where post_id =:post_id  ", nativeQuery = true)
    int countLike(@Param("post_id") Long post_id);

    Likes findByUserIdAndPostId(Long user_id, Long post_id);



}
