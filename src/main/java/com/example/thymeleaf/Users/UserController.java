package com.example.thymeleaf.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

// Libraries for hashing
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    private UserService userService; 

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login"; 
    }

    @PostMapping("/")
    public String processLogin(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model) {
        User foundUser = userService.findByUsername(user.getUsername());
        
        // Hash the received password
        String hashedInputPassword = hashString(user.getPassword());

        // Compare the hashed input password with the stored hashed password
        if (foundUser != null && foundUser.getPassword().equals(hashedInputPassword)) {
            redirectAttributes.addFlashAttribute("foundUser", foundUser);
            return "redirect:/products"; // Redirect to the products page after login
        }

        // Cannot find the user
        model.addAttribute("error", "Invalid username or password");
        return "login"; // Return to the login page with an error
    }

    // Function to hash a password by SHA-256
    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}