package com.example.testingexercises.service;

import com.example.testingexercises.entity.User;
import com.example.testingexercises.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    private final UserRepository userRepository;
    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    

    public String getUserNameById(Long id) {
        return userRepository.findById(id)
                .map(User::getName)
                .orElse("Unknown");
    }
}