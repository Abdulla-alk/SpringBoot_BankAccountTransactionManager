package com.example.accountManager.controller;

import com.example.accountManager.entity.AccountType;
import com.example.accountManager.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountTypes")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @GetMapping
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        List<AccountType> types = accountTypeService.getAllAccountTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountType> getAccountTypeById(@PathVariable Long id) {
        AccountType type = accountTypeService.getAccountTypeById(id);
        return type != null ? ResponseEntity.ok(type) : ResponseEntity.notFound().build();
    }
}
