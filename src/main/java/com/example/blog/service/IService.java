package com.example.blog.service;

import com.example.blog.model.Post_Label;

import java.util.Optional;

public interface IService <T>{
    Optional<T> findById(Long id);
    Iterable<T> findAll();
    Post_Label save(T t);
    void remove(Long id);
}