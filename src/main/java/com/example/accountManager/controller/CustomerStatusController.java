package com.example.accountManager.controller;

import com.example.accountManager.entity.CustomerStatus;
import com.example.accountManager.service.CustomerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerStatuses")
public class CustomerStatusController {

    @Autowired
    private CustomerStatusService customerStatusService;

    @GetMapping
    public ResponseEntity<List<CustomerStatus>> getAllCustomerStatuses() {
        List<CustomerStatus> statuses = customerStatusService.getAllCustomerStatuses();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerStatus> getCustomerStatusById(@PathVariable Long id) {
        CustomerStatus status = customerStatusService.getCustomerStatusById(id);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }
}
