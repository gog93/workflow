package com.example.workflow.repository;

import com.example.workflow.model.Unselected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnselectedRepository extends JpaRepository<Unselected, Long> {
    Optional<Unselected> findByCustomerId(Long id);

}
