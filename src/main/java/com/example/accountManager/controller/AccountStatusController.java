package com.example.accountManager.controller;

import com.example.accountManager.entity.AccountStatus;
import com.example.accountManager.service.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountStatuses")
public class AccountStatusController {

    @Autowired
    private AccountStatusService accountStatusService;

    @GetMapping
    public ResponseEntity<List<AccountStatus>> getAllAccountStatuses() {
        List<AccountStatus> statuses = accountStatusService.getAllAccountStatuses();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountStatus> getAccountStatusById(@PathVariable Long id) {
        AccountStatus status = accountStatusService.getAccountStatusById(id);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }
}
