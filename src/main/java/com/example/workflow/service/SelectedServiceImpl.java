package com.example.workflow.service;

import com.example.workflow.model.Selected;
import com.example.workflow.repository.SelectedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelectedServiceImpl {
    private final SelectedRepository selectedRepository;

    public Selected save(Selected selected) {

       return selectedRepository.save(selected);
    }

    public void delete() {
        selectedRepository.deleteAll();
    }


    public List<Selected> findAll() {
        return selectedRepository.findAll();
    }

    public void update(Long customerId) {
        Selected selected=selectedRepository.findByCustomerId(customerId);
        selected.setStatus("selected");
        selectedRepository.save(selected);
    }


    public List<Selected> findByStatus(String selected) {
        return selectedRepository.findByStatus(selected);
    }
}
