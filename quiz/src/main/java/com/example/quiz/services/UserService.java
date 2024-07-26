package com.example.quiz.services;

import com.example.quiz.dao.UserRepository;
import com.example.quiz.models.Question;
import com.example.quiz.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionService questionService;

    public User userRegistration(User user){
        userRepository.save(user);
        return user;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getByPasswordAndEmail(String password,String email){
        return userRepository.findByPasswordAndEmail(password, email);

    }
    public User getById(long id){
        return  userRepository.findById(id).get();
    }
    public User validAnswer(int player_id,int question_id,String answer){
        User user=getById(player_id);
        Question question=questionService.getQuestionById(question_id).get();
        System.out.println("Question :"+question);
        System.out.println("Answer: "+answer);
        System.out.println(question.getAnswer().equals(answer));
        if(questionService.getQuestionById(question_id).get().getAnswer().equals(answer)){
            System.out.println("updating score :");
            user.setCurScore(user.getCurScore()+1);
            userRepository.save(user);

        }
        if(question_id==10){
            if(user.getCurScore()>user.getPrevScore()){
                user.setPrevScore(user.getCurScore());
            }
            user.setCurScore(0);
            userRepository.save(user);
        }

        return user;
    }
    public List<User> highestUsers() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparingInt(User::getCurScore).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

}
