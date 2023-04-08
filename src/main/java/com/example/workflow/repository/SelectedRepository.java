package com.example.workflow.repository;

import com.example.workflow.model.Selected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelectedRepository extends JpaRepository<Selected, Long> {

    Selected findByCustomerId(Long customerId);

    List<Selected> findByStatus(String selected);
}
