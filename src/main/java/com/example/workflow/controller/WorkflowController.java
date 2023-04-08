package com.example.workflow.controller;

import com.example.workflow.model.Customer;
import com.example.workflow.model.Selected;
import com.example.workflow.model.Workflow;
import com.example.workflow.service.CustomerService;
import com.example.workflow.service.SelectedServiceImpl;
import com.example.workflow.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;
    private final CustomerService customerService;
    private final SelectedServiceImpl selectedService;

    @GetMapping("/{id}")
    public String workflow(Model model, @PathVariable("id") Long id) {
        selectedService.delete();
        List<Selected> all=selectedService.findAll();
        for(Customer customer:workflowService.findById(id).get().getCustomer() ){
            selectedService.save(new Selected(1L,"selected", customer));
        }
        Workflow workflowById = workflowService.findById(id).get();
        List<Customer> customerById = workflowService.findById(id).get().getCustomer();
        List<Customer> allCustomer = new ArrayList<>();
        for (Customer customer : customerService.findAll()) {
            if (!checkWorkFlowInCustomer(customer,id)) {
                allCustomer.add(customer);
            }
        }

        model.addAttribute("customerById", customerById);
        model.addAttribute("customers", allCustomer);
        model.addAttribute("customer", new Customer());
        model.addAttribute("workflowById", workflowById);
        model.addAttribute("workflow", new Workflow());

        return "workflowSection";
    }

    @GetMapping()
    public String workflowEdit(Model model) {
        model.addAttribute("allEmails", customerService.findAll());
        model.addAttribute("workflow", new Workflow());
        model.addAttribute("customer", new Customer());
        return "workflow";
    }

    @PostMapping("/{id}")
    public String save(@ModelAttribute("workflow") @Valid Workflow workflow, BindingResult bindingResult,
                       Model model, @PathVariable("id") Long id,
                       @RequestParam(value = "uploadFile", required = false) MultipartFile multipartFile) {
        Workflow workflowById = workflowService.findById(id).get();
        List<Customer> allCustomer = new ArrayList<>();
        for (Customer customer : customerService.findAll()) {
                if (!checkWorkFlowInCustomer(customer,id)) {
                    allCustomer.add(customer);
                }
            }

        model.addAttribute("customers", allCustomer);
        model.addAttribute("workflowById", workflowById);
        if (workflow.getName().equals("") && workflow.getDescription().equals("")) {
            model.addAttribute("blank", "Workflow name can not be null");
            model.addAttribute("blankDescription", "Workflow description can not be null");
            return "workflowSection";

        }
        if (workflow.getDescription().equals("")) {
            model.addAttribute("blankDescription", "Workflow description can not be null");
            return "workflowSection";
        }
        if (workflow.getName().equals("")) {
            model.addAttribute("blank", "Workflow name can not be null");
            return "workflowSection";
        }
        Workflow savedParentDto = workflowService.update(workflow, id);
        if (!multipartFile.isEmpty()) {
            workflowService.addImage(savedParentDto, multipartFile);
        }

        return "redirect:/workflow/" + id;
    }

    @PostMapping()
    public String save1(Workflow workflow, Model model,
                        @RequestParam(value = "upload", required = false) MultipartFile multipartFile) {
        model.addAttribute("customer", new Customer());

        model.addAttribute("allEmails", customerService.findAll());

        Workflow savedParentDto = workflowService.save(workflow);
        if (!multipartFile.isEmpty()) {
            workflowService.addImage(savedParentDto, multipartFile);
        }

        return "workflow";
    }

    private boolean checkWorkFlowInCustomer(Customer customer, Long id) {
        boolean check = false;
            for (Workflow workflowBy : customer.getWorkflow()) {
                if (workflowBy.getId() == id) {
                    check = true;
                }
        }
        return check;
    }

}
