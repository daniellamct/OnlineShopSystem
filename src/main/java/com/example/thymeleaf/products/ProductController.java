package com.example.thymeleaf.products;

import org.springframework.beans.factory.annotation.Autowired;
/* import org.springframework.http.ResponseEntity; */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import Model
import org.springframework.web.bind.annotation.GetMapping;
/* import org.springframework.web.bind.annotation.RequestMapping; */
/* import org.springframework.web.bind.annotation.RestController; */
import org.springframework.web.bind.annotation.ModelAttribute;

// extra library for mapping java object into json
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import com.example.thymeleaf.Users.User;

@Controller
public class ProductController {

    @Autowired
    private ProductServices productService; 

    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper



    @GetMapping("/products")
    public String viewHomePage(Model model, @ModelAttribute("foundUser") User foundUser, HttpSession session) { // Added Model parameter

        if (foundUser == null || foundUser.getUsername() == null || foundUser.getUsername().isEmpty()) {
            return "redirect:/"; 
    
        }

        // Fetch products from the service
        List<Product> products = productService.getAllProducts();
    
        // Convert products list to JSON string
        try {
            String productsJson = objectMapper.writeValueAsString(products);
            model.addAttribute("productsJson", productsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("products", products);


        model.addAttribute("user", foundUser); // Add foundUser to model if needed


        return "index";  
    }


}

