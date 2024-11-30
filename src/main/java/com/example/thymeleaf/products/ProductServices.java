package com.example.thymeleaf.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Retrieve all products
    }

    public Product save(Product product) {
        return productRepository.save(product); // Save a new or updated product
    }

    public Product getById(String id) {
        return productRepository.findById(id).orElse(null); // Retrieve by ID
    }

    public void deleteById(String id) {
        productRepository.deleteById(id); // Delete by ID
    }
}