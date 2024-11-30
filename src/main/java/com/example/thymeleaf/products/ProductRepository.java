package com.example.thymeleaf.products;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository // Optional but recommended for clarity
public interface ProductRepository extends MongoRepository<Product, String> {
    // Custom query methods can be defined here if needed
}