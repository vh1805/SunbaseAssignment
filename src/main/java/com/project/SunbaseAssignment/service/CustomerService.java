package com.project.SunbaseAssignment.service;

import com.project.SunbaseAssignment.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<String> createCustomer(Customer customer);

    List<Customer> getCustomerList();

    ResponseEntity<String> delete(Long customerId);

    ResponseEntity<String> update(Customer customer, Long customerId);
}
