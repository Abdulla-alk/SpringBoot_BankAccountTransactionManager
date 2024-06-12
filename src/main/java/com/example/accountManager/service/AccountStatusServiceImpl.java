package com.example.accountManager.service;

import com.example.accountManager.entity.AccountStatus;
import com.example.accountManager.repository.AccountStatusRepository;
import com.example.accountManager.service.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Override
    public List<AccountStatus> getAllAccountStatuses() {
        return accountStatusRepository.findAll();
    }

    @Override
    public AccountStatus getAccountStatusById(Long id) {
        return accountStatusRepository.findById(id).orElse(null);
    }
}
