package com.example.blog.service.impl;

import com.example.blog.model.Label;
import com.example.blog.repository.LabelRepository;
import com.example.blog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    public Iterable<Label> findAll(Pageable pageable) {
        return labelRepository.findAll(pageable);
    }

    @Override
    public Optional<Label> findById(Long id) {
        return labelRepository.findById(id);
    }

    @Override
    public Iterable<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public void save(Label label) {
        labelRepository.save(label);
    }

    @Override
    public void remove(Long id) {
        labelRepository.deleteById(id);
    }
}

