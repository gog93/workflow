package com.example.workflow.repository;


import com.example.workflow.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Set<Customer> findAllCustomersByWorkflowId(Long id);
}
