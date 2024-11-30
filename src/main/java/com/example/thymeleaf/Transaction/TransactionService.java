package com.example.thymeleaf.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

  
    public Transaction registerTransaction(Transaction transaction) {
        return transactionRepository.save(transaction); // Save user to MongoDB
    }

    

}