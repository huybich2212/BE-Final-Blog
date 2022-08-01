package com.example.blog.model;

import javax.persistence.*;

@Entity
@Table(name = "post_rate")
public class Post_Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Rate rate;

    @ManyToOne
    private User user;

    public Post_Rate() {
    }

    public Post_Rate(Long id, Post post, Rate rate, User user) {
        this.id = id;
        this.post = post;
        this.rate = rate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
