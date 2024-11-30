package com.example.thymeleaf.Admin;

/* import java.util.List; */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/* import com.example.thymeleaf.Users.User; */
import com.example.thymeleaf.products.Product;
import com.example.thymeleaf.products.ProductServices;
/* import com.fasterxml.jackson.core.JsonProcessingException; */

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService; 

    @Autowired
    private ProductServices productServices;

    @GetMapping("/adminLogin")
    public String showLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        for(int i = 0; i < 50; i++){
            System.out.println("Getting");
        }
        return "adminLogin"; 
    }
    

    @PostMapping("/adminLogin")
    public String adminProcessLogin(@ModelAttribute Admin admin, RedirectAttributes redirectAttributes, Model model) {

        Admin foundAdmin = adminService.findByUsername(admin.getUsername());
        
        // Found the user
        if (foundAdmin != null && foundAdmin.getPassword().equals(admin.getPassword())) {
            redirectAttributes.addFlashAttribute("foundAdmin", foundAdmin);
            return "redirect:/adminManage"; // Redirect to the products page after login
        }
        
        // Cannot find the user
        model.addAttribute("error", "Invalid username or password");
        return "adminLogin"; // Return to the login page with an error
    }

    @GetMapping("/adminManage")
    public String viewHomePage(Model model, @ModelAttribute("foundAdmin") Admin foundAdmin, HttpSession session) { // Added Model parameter

        if (foundAdmin == null || foundAdmin.getUsername() == null || foundAdmin.getUsername().isEmpty()) {
            System.out.println();
            /* return "redirect:/";  */
        }
        model.addAttribute("admin", foundAdmin); // Add foundUser to model if needed

        model.addAttribute("allemplist", productServices.getAllProducts());
        return "admin";  
    }

    @GetMapping("/update/{id}")
	public String updateForm(@PathVariable(value = "id") String id, Model model) {
		Product product = productServices.getById(id);
		model.addAttribute("product", product);
        System.out.println(product.getId());
		return "update";
	}

    @PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productServices.save(product);
        System.out.println(product.getId());
        System.out.println(product.getJs_id());
        System.out.println(product.getName());
        System.out.println(product.getRegularPrice());
        System.out.println(product.getProductCategory());
        
		return "redirect:/adminManage";
	}
}