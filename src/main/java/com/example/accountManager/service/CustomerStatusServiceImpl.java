package com.example.accountManager.service;

import com.example.accountManager.entity.CustomerStatus;
import com.example.accountManager.repository.CustomerStatusRepository;
import com.example.accountManager.service.CustomerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService {

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Override
    public List<CustomerStatus> getAllCustomerStatuses() {
        return customerStatusRepository.findAll();
    }

    @Override
    public CustomerStatus getCustomerStatusById(Long id) {
        return customerStatusRepository.findById(id).orElse(null);
    }
}
