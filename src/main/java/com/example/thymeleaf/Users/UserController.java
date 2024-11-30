package com.example.thymeleaf.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    @Autowired
    private UserService userService; 

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Return the registration view
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userRepository.save(user); // Save user to MongoDB
        return "redirect:/login"; // Redirect to login page after registration
    }

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        for(int i = 0; i < 50; i++){
            System.out.println("Getting");
        }
        return "login"; 
    }


    @PostMapping("/")
    public String processLogin(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model) {

        User foundUser = userService.findByUsername(user.getUsername());
        // found the user
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            redirectAttributes.addFlashAttribute("foundUser", foundUser);
            return "redirect:/products"; // Redirect to the products page after login
        }
        // cannot find the user
        model.addAttribute("error", "Invalid username or password");
        return "login"; // Return to the login page with an error
    }


}