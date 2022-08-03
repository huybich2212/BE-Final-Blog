package com.example.blog.service.impl;

import com.example.blog.model.Post_Label;
import com.example.blog.model.Rate;
import com.example.blog.repository.RateRepository;
import com.example.blog.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;


    public Iterable<Rate> findAll(Pageable pageable) {
        return rateRepository.findAll(pageable);
    }

    @Override
    public Optional<Rate> findById(Long id) {
        return rateRepository.findById(id);
    }

    @Override
    public Iterable<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public Post_Label save(Rate rate) {
        rateRepository.save(rate);
        return null;
    }

    @Override
    public void remove(Long id) {
        rateRepository.deleteById(id);
    }
}
