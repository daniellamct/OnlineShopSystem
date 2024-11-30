package com.example.thymeleaf.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

  
    public Admin registerUser(Admin admin) {
        return adminRepository.save(admin); // Save user to MongoDB
    }

    
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username); // Find user by username
    }
}