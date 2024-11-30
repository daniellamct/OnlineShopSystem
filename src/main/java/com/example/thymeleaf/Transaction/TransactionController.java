package com.example.thymeleaf.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/* import org.springframework.stereotype.Controller; */
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions") // Base URL for your API
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.registerTransaction(transaction);
        return ResponseEntity.ok(savedTransaction); // Return the saved transaction
    }
}