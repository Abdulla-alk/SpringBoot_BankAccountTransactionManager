package com.example.accountManager.service;

import com.example.accountManager.entity.Account;
import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.repository.AccountRepository;
import com.example.accountManager.repository.AccountTransactionRepository;
import com.example.accountManager.service.AccountService;
import com.example.accountManager.util.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountTransactionRepository accountTransactionRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setId(5L);
        account.setBalance(BigDecimal.valueOf(1000.00));
    }

    @Test
    public void testTopUpAccount() {
        when(accountRepository.findById(5L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        BigDecimal amount = BigDecimal.valueOf(100.00);
        Account updatedAccount = accountServiceImpl.topUpAccount(5L, amount);

        assertNotNull(updatedAccount);
        assertEquals(BigDecimal.valueOf(1100.00), updatedAccount.getBalance());

        verify(accountRepository, times(1)).findById(5L);
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(accountTransactionRepository, times(1)).save(any(AccountTransaction.class));
    }

    @Test
    public void testWithdrawFromAccount() {
        when(accountRepository.findById(5L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        BigDecimal amount = BigDecimal.valueOf(100.00);
        Account updatedAccount = accountServiceImpl.withdrawFromAccount(5L, amount);

        assertNotNull(updatedAccount);
        assertEquals(BigDecimal.valueOf(900.00), updatedAccount.getBalance());

        verify(accountRepository, times(1)).findById(5L);
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(accountTransactionRepository, times(1)).save(any(AccountTransaction.class));
    }

}
