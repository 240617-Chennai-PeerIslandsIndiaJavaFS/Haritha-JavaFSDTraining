package com.example.quiz.services;

import com.example.quiz.dao.QuestionRepository;
import com.example.quiz.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public List<Question> createQuestion(List<Question> question){
        return questionRepository.saveAll(question);

    }
    public Optional<Question> getQuestionById(long id){
        return  questionRepository.findById(id);
    }
}
