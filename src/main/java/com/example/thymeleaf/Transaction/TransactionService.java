package com.example.thymeleaf.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

  
    public Transaction registerTransaction(Transaction transaction) {
        return transactionRepository.save(transaction); // Save transaction to MongoDB
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll(); // Retrieve all transaction
    }

}