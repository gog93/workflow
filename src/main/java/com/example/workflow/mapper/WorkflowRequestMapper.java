package com.example.workflow.mapper;

import com.example.workflow.dto.RequestWorkflow;
import com.example.workflow.model.Workflow;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class WorkflowRequestMapper implements Mapper<Workflow, RequestWorkflow> {

        @Override
        public RequestWorkflow mapToDTO(Workflow workflow) {
            return RequestWorkflow.builder()
                    .name(workflow.getName())
                    .description(workflow.getDescription())
                    .uploadFile(workflow.getUploadFile())
                    .updated(workflow.getUpdatedAt())
                    .customerList(workflow.getCustomer())
                    .build();
        }

        @Override
        public Workflow mapToOBJ(RequestWorkflow requestWorkflow) {
            return Workflow.builder()
                    .name(requestWorkflow.getName())
                    .description(requestWorkflow.getDescription())
                    .uploadFile(requestWorkflow.getUploadFile())
                    .customer(requestWorkflow.getCustomerList())
                    .updatedAt(requestWorkflow.getUpdated())
                    .build();
        }
    }

