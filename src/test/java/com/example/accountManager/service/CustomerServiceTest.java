package com.example.accountManager.service;

import com.example.accountManager.entity.Customer;
import com.example.accountManager.repository.CustomerRepository;
import com.example.accountManager.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer created = customerService.createCustomer(customer);
        assertNotNull(created);
        assertEquals("John", created.getFirstName());
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(2, customers.size());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer found = customerService.getCustomerById(1L);

        assertNotNull(found);
        assertEquals("John", found.getFirstName());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer updated = customerService.updateCustomer(1L, customer);

        assertNotNull(updated);
        assertEquals("John", updated.getFirstName());
    }

}
