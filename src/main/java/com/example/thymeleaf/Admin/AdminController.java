package com.example.thymeleaf.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.thymeleaf.Transaction.TransactionService;
import com.example.thymeleaf.Users.UserService;
import com.example.thymeleaf.products.Product;
import com.example.thymeleaf.products.ProductServices;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService; 

    @Autowired
    private ProductServices productServices;

    @Autowired
    private UserService userServices;

    @Autowired
    private TransactionService transactionServices;

    @GetMapping("/adminLogin")
    public String showLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminLogin"; 
    }
    

@PostMapping("/adminLogin")
public String adminProcessLogin(@ModelAttribute Admin admin, RedirectAttributes redirectAttributes, Model model) {
    Admin foundAdmin = adminService.findByUsername(admin.getUsername());

    // Hash the received password
    String hashedInputPassword = hashString(admin.getPassword());
    
    // If the passwords matches, redirect user to the next page
    if (foundAdmin != null && foundAdmin.getPassword().equals(hashedInputPassword)) {
        redirectAttributes.addFlashAttribute("admin", admin);
        return "redirect:/admin"; 
    }
    
    // Wrong username or password
    model.addAttribute("error", "Invalid username or password");
    return "adminLogin"; // Return to the login page with an error
}

@GetMapping("/admin")
public String viewHomePage(Model model) {
    model.addAttribute("allemplistProducts", productServices.getAllProducts());
    model.addAttribute("allemplistUsers", userServices.getAllUsers());
    model.addAttribute("allemplistTransactions", transactionServices.getAllTransactions());
    return "admin";  
}

@GetMapping("/update/{id}")
public String updateForm(@PathVariable(value = "id") String id, Model model) {
    Product product = productServices.getById(id);
    model.addAttribute("product", product);
    return "update";
}

@PostMapping("/save")
public String saveProduct(@ModelAttribute("product") Product product) {
    productServices.save(product);
    return "redirect:/admin";
}

    // Function to hash a password with SHA-256
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