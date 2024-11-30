package com.example.thymeleaf.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

  
    public User registerUser(User user) {
        return userRepository.save(user); // Save user to MongoDB
    }

    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // Find user by username
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(); // Retrieve all products
    }
}