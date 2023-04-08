package com.example.workflow.service;

import com.example.workflow.model.Customer;
import com.example.workflow.repository.CustomerRepository;
import com.example.workflow.repository.UnselectedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UnselectedRepository unselectedRepository;

    public CustomerServiceImpl(CustomerRepository userEmailRepository,
                               UnselectedRepository unselectedRepository) {
        this.customerRepository = userEmailRepository;
        this.unselectedRepository = unselectedRepository;
    }


    @Override
    public Customer save(Customer userEmails) {
        return customerRepository.save(userEmails);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Set<Customer> findAllCustomersByWorkflowId(Long id) {
        return customerRepository.findAllCustomersByWorkflowId(id);
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }



}
