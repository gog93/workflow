package com.example.workflow.service;

import com.example.workflow.model.Workflow;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface WorkflowService {

    Workflow save(Workflow workflow );

    List<Workflow> findAll();

    void addImage(Workflow savedParentDto, MultipartFile multipartFile);

    Optional<Workflow> findById(Long id);

    Workflow update(Workflow workflow, Long id);
}
