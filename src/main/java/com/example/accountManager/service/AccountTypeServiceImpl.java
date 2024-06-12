package com.example.accountManager.service;

import com.example.accountManager.entity.AccountType;
import com.example.accountManager.repository.AccountTypeRepository;
import com.example.accountManager.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    @Override
    public AccountType getAccountTypeById(Long id) {
        return accountTypeRepository.findById(id).orElse(null);
    }
}
