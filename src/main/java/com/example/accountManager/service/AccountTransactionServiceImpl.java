package com.example.accountManager.service;

import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.repository.AccountTransactionRepository;
import com.example.accountManager.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Override
    public AccountTransaction createTransaction(AccountTransaction transaction) {
        return accountTransactionRepository.save(transaction);
    }

    @Override
    public List<AccountTransaction> getAllTransactions() {
        return accountTransactionRepository.findAll();
    }

    @Override
    public AccountTransaction getTransactionById(Long id) {
        return accountTransactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AccountTransaction> getTransactionsByAccountId(Long accountId) {
        return accountTransactionRepository.findByAccountId(accountId);

    }
}
