package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post WHERE id = :id", nativeQuery = true)
    Iterable<Post> findAllById(@Param("id") Long id);

    @Override
    Optional<Post> findById(Long id);
    @Query(value = "SELECT * FROM post WHERE status = 1", nativeQuery = true)
    Iterable<Post> findAllByStatus();

    //find all post by name containing and user id
    @Query(value = "SELECT * FROM post WHERE title LIKE %:name% AND user_id = :userId", nativeQuery = true)
    Iterable<Post> findAllByTitleAndUserId(@Param("name") String title, @Param("userId") Long userId);
    //find all post by name containing and user id
Iterable<Post> findAllByTitleContainingAndUserId(String title, Long userId);

    //find all post with label id and user id
    @Query(value = "select  * from post  join post_lable pl on post.id = pl.post_id\n" +
            "join label l on l.id = pl.label_id\n" +
            "where l.id = :labelId and post.user_id = :userId\n", nativeQuery = true)
    Iterable<Post> findAllByLabelIdAndUserId(@Param("labelId") Long labelId, @Param("userId") Long userId);

}

