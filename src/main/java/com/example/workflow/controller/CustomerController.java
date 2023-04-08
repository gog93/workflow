package com.example.workflow.controller;

import com.example.workflow.model.Customer;
import com.example.workflow.repository.CustomerRepository;
import com.example.workflow.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customerss")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping
    public String add(Model model, Customer customer) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customer", customerRepository.findAll());
        customerRepository.save(customer);
        return "redirect:/workflow";
    }

    @GetMapping()
    public String customer(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers",customers);
        model.addAttribute("customer", new Customer());

        return "index";
    }}
