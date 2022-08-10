package com.example.blog.repository;

import com.example.blog.model.Post_Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface Post_LabelRepository extends JpaRepository<Post_Label, Long> {
    //find all label with post id
    Iterable<Post_Label> findAllByPostId(Long postId);

    Iterable<Post_Label>findPost_LabelByPostId(Long postId);

    //find all post with label id
//    Iterable<Post_Label> findAllByLabelId(@PathVariable Long labelId);

    @Query(value = "SELECT *\n" +
            "FROM post_lable join post p on p.id = post_lable.post_id\n" +
            "where label_id = :labelId \n" +
            "order by create_at desc", nativeQuery = true)
    Iterable<Post_Label> findAllByLabelId( @Param("labelId") Long labelId);

    //delete post-label by post id and label id
    @Query(value = "delete from Post_Label p where p.postId = :postId and p.labelId = :labelId", nativeQuery = true)
    void deleteByPostIdAndLabelId(@Param("postId") Long postId, @Param("labelId") Long labelId);

    //get list label and number of post with label id
    @Query(value = "select p.label_id, l.name, count(p.post_id) as numberOfPost " +
            "from post_lable p " +
            "join label l on l.id = p.label_id " +
            " group by p.label_id", nativeQuery = true)
    Iterable<String> getListLabelAndNumberOfPostPerLabel();

}

