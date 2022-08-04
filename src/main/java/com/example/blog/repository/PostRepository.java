package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //delete post by id and user id
    @Query(value = "DELETE FROM post WHERE id = :id AND user_id = :userId", nativeQuery = true)
    void deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "SELECT * FROM post WHERE user_id = :id ORDER BY create_at DESC ", nativeQuery = true)
    Iterable<Post> findAllById(@Param("id") Long id);
    @Query(value = "SELECT * FROM post\n" +
            "WHERE status = 1 OR (status =0 AND user_id LIKE :id) ORDER BY create_at DESC ", nativeQuery = true)
    Iterable<Post> findAllPostCustom(@Param("id") Long id);

    @Override
    Optional<Post> findById(Long id);

    @Query(value = "SELECT * FROM post WHERE status = 1 ORDER BY create_at DESC ", nativeQuery = true)
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

    Iterable<Post> findByTitleContaining(String title);

    //find post by title containing and status = 1
    @Query(value = "SELECT * FROM post WHERE title LIKE %:name% AND status = 1", nativeQuery = true)
    Iterable<Post> findAllByTitleAndStatus(@Param("name") String title);

    //find post by title
    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByContentContaining(String content);

    //find post with number of likes desc limit 5
    @Query(value = "SELECT * FROM post WHERE status = 1 ORDER BY number_of_like DESC LIMIT 5", nativeQuery = true)
    Iterable<Post> findAllByNumberOfLikesDesc();

    //find all order by create at desc
    @Query(value = "SELECT * FROM post ORDER BY create_at DESC", nativeQuery = true)
    Iterable<Post> findAllOrderByCreateAtDesc();


}

