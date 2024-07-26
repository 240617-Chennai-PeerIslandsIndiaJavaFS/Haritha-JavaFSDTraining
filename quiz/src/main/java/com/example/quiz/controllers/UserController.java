package com.example.quiz.controllers;

import com.example.quiz.models.Question;
import com.example.quiz.models.User;
import com.example.quiz.services.QuestionService;
import com.example.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
       return new ResponseEntity<>(userService.userRegistration(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
       return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<User> userLogin(@RequestParam String password,@RequestParam String email){
       return new ResponseEntity<>(userService.getByPasswordAndEmail(password, email),HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getQuestionById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/play")
    public ResponseEntity<String> validAnswer(@RequestParam int user_id, @RequestParam int question_id, @RequestParam String answer) {
        User user = userService.validAnswer(user_id, question_id, answer);
        String responseMessage;

        if (user_id == 10) {
            responseMessage = "Thank you ! your score: " + user.getCurScore();
        } else {
            responseMessage = "Update score: " + user.getCurScore();
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @PostMapping("/home")
    public ResponseEntity<List<User>> highestPlayers() {
        List<User> topPlayers = userService.highestUsers();
        return new ResponseEntity<>(topPlayers, HttpStatus.OK);
    }

}
