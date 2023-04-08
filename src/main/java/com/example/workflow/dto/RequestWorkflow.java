package com.example.workflow.dto;

import com.example.workflow.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestWorkflow {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    private String uploadFile;
    private Long updated;
  private List<Customer> customerList;

}
