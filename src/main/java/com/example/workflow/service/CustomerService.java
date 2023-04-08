package com.example.workflow.service;

import com.example.workflow.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerService {

    Customer save(Customer userEmails);
    List<Customer> findAll( );

    Set<Customer> findAllCustomersByWorkflowId(Long id);

    Optional<Customer> findById(Long customerId);

}
