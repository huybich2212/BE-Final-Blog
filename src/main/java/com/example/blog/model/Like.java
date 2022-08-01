package com.example.blog.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    private LocalDateTime dateTime;

    public Like() {
    }

    public Like(Long id, User user, Post post, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
