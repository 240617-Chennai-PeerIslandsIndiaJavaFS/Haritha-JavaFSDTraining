package com.example.quiz.dao;

import com.example.quiz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByPasswordAndEmail(String password,String email);

}
