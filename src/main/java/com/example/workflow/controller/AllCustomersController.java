package org.medical.hub.customer.service.impl;

import com.example.workflow.model.Customer;
import com.example.workflow.model.Selected;
import com.example.workflow.repository.CustomerRepository;
import com.example.workflow.repository.SelectedRepository;
import com.example.workflow.service.CustomerService;
import com.example.workflow.service.SelectedServiceImpl;
import com.example.workflow.service.UnelectedServiceImpl;
import com.example.workflow.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class AllCustomersController {
    private final CustomerService customerService;
    private final WorkflowService workflowService;
    private final SelectedServiceImpl selectedService;
    private final UnelectedServiceImpl unselectedService;
    private final CustomerRepository customerRepository;
    private final SelectedRepository selectedRepository;


    @GetMapping("/customers")
    public @ResponseBody
    List<Customer> findAllTeacherInCourseById() {
        List<Customer> allCustomer = new ArrayList<>();
        List<Customer> allSelected = new ArrayList<>();
      List<Selected> customerInSelected=  selectedService.findAll();
      for (Selected selected: customerInSelected){
          allSelected.add(selected.getCustomer());
      }
      for (Customer customer : customerService.findAll()) {
                if (!allSelected.contains(customer)){
                selectedService.save(new Selected(1L, "unselected", customer));
                allCustomer.add(customer);
            }

        }
        List<Selected> unselected = selectedService.findByStatus("unselected");
        for (Selected u: unselected) {
            if (!allCustomer.contains(u.getCustomer())) {
                allCustomer.add(u.getCustomer());
            }
        }
        return allCustomer;
    }

    @PostMapping("/workflows/{id}/addCustomer")
    @ResponseBody
    public ResponseEntity<Void> addCustomerToWorkflow(@PathVariable("id") Long id, @RequestParam Long customerId) {

        System.out.println(customerId);

        selectedService.update(customerId);
        Customer customer = customerService.findById(customerId).get();
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/workflows/{id}")
    public @ResponseBody
    List<Customer> findAllCustomerInWorkflowById(@PathVariable("id") Long id) {
        List<Customer> allCustomer = new ArrayList<>();

        for (Selected selected : selectedService.findAll()) {
            if (("selected").equals(selected.getStatus())) {
                allCustomer.add(selected.getCustomer());
            }
        }

        return allCustomer;
    }

}

