package com.example.blog.controller;

import com.example.blog.model.Label;
import com.example.blog.service.impl.LabelServiceImpl;
import com.example.blog.service.impl.Post_LabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/labels")
public class LabelController {
    @Autowired
    private LabelServiceImpl labelService;

    // get all labels

    @GetMapping("")
    public ResponseEntity<Iterable<Label>> showAllLabel() {
        Iterable<Label> labels = labelService.findAll();
        return new ResponseEntity<>(labels, HttpStatus.OK);
    }

    // new label
    @PostMapping("")
    public ResponseEntity<Label> createLabel(@RequestBody Label label) {
        labelService.save(label);
        return new ResponseEntity<>(label, HttpStatus.CREATED);
    }

    //delete label
    @DeleteMapping("/{id}")
    public ResponseEntity<Label> deleteLabel(@PathVariable Long id) {
        labelService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //update label
    @PutMapping("/{id}")
    public ResponseEntity<Label> updateLabel(@PathVariable Long id,@RequestBody Label label) {
        Optional<Label> label1 = labelService.findById(id);
        if (!label1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        label.setId(label1.get().getId());
        labelService.save(label);
        return new ResponseEntity<>(label,HttpStatus.CREATED);
    }
}
