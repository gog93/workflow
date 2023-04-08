package com.example.workflow.dto;

import com.example.workflow.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseWorkflow {
    private Long id;

    private String name;
    private List<Customer> customerList;

}
