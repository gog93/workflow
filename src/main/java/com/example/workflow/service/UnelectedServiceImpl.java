package com.example.workflow.service;

import com.example.workflow.model.Customer;
import com.example.workflow.model.Unselected;
import com.example.workflow.model.Workflow;
import com.example.workflow.repository.UnselectedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnelectedServiceImpl {
    private final UnselectedRepository unselectedRepository;
    private final CustomerService customerService;

    public Unselected save(Unselected unselected) {
        return unselectedRepository.save(unselected);
    }

    public List<Unselected> findAll() {
        return unselectedRepository.findAll();
    }

    public void delete() {
        unselectedRepository.deleteAll();
    }

    public Optional<Unselected> findById(Long id) {
        return unselectedRepository.findById(id);
    }



    public Optional<Unselected> findByCustomerId(Long id) {
        return unselectedRepository.findByCustomerId(id);
    }


    public void update(Unselected unselected,Long customerId) {
        Unselected unselected1=findByCustomerId(customerId).get();
        unselected1.getCustomer().remove(customerService.findById(customerId).get());
        save(unselected);
    }

    public void deleteObj(Unselected byCustomerId) {
        unselectedRepository.delete(byCustomerId);
    }
}
