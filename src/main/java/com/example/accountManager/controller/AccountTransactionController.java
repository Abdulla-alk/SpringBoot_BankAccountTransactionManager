package com.example.accountManager.controller;

import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class AccountTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;
    @GetMapping
    public ResponseEntity<List<AccountTransaction>> getAllTransactions() {
        List<AccountTransaction> transactions = accountTransactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTransaction> getTransactionById(@PathVariable Long id) {
        AccountTransaction transaction = accountTransactionService.getTransactionById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<AccountTransaction>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<AccountTransaction> transactions = accountTransactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }
}
