package com.example.accountManager.service;

import com.example.accountManager.entity.Account;
import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.repository.AccountRepository;
import com.example.accountManager.repository.AccountTransactionRepository;
import com.example.accountManager.service.AccountService;
import com.example.accountManager.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public Account updateAccount(Long id, Account accountDetails) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setAccountNumber(accountDetails.getAccountNumber());
            account.setAccountType(accountDetails.getAccountType());
            account.setBalance(accountDetails.getBalance());
            account.setCustomer(accountDetails.getCustomer());
            account.setStatus(accountDetails.getStatus());
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account topUpAccount(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);

            // Create and save the transaction record
            AccountTransaction transaction = new AccountTransaction();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.TOPUP);
            transaction.setTransactionDate(LocalDateTime.now());
            accountTransactionRepository.save(transaction);
        }
        return account;
    }


    @Override
    @Transactional
    public Account withdrawFromAccount(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            AccountTransaction transaction = new AccountTransaction();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.WITHDRAWAL);
            transaction.setTransactionDate(LocalDateTime.now());
            accountTransactionRepository.save(transaction);
        }
        return account;
    }
}
