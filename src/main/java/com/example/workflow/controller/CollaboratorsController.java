package com.example.workflow.controller;

import com.example.workflow.service.SelectedServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CollaboratorsController {
    private final SelectedServiceImpl collaboratorsService;
}
