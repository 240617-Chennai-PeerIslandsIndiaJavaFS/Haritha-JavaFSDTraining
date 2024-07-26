package com.example.quiz.controllers;


import com.example.quiz.models.Question;
import com.example.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<List<Question>> createQuestion(@RequestBody List<Question> question) {
        return new ResponseEntity<>(questionService.createQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestion(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return new ResponseEntity<>(questionService.getQuestionById(id).get(),HttpStatus.OK);
    }

}
