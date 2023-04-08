package com.example.workflow.service;

import com.example.workflow.model.Customer;
import com.example.workflow.model.Workflow;
import com.example.workflow.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService{
  private final String upload = System.getProperty("user.home") + "/img/";

  private final   WorkflowRepository workflowRepository;


    @Override
    public Workflow save(Workflow workflow) {

   return workflowRepository.save(workflow);
    }

  @Override
  public List<Workflow> findAll() {
    return workflowRepository.findAll();
  }

  @Override
  public void addImage(Workflow workflow, MultipartFile multipartFile) {
    Workflow workflowById = workflowRepository.findById(workflow.getId()).get();
    workflowById.setUploadFile(saveImage(multipartFile));
    workflowRepository.save(workflow);
  }

  @Override
  public Optional<Workflow> findById(Long id) {
    return workflowRepository.findById(id);
  }

  @Override
  public Workflow update(Workflow workflow, Long id) {
      Workflow byId=findById(id).get();
      byId.setName(workflow.getName());
      byId.setUploadFile(workflow.getUploadFile());
      byId.setDescription(workflow.getDescription());
    for (Customer customer : workflow.getCustomer()) {
      byId.getCustomer().add(customer);
    }
    save(byId);
    return byId;
  }

  private String saveImage(MultipartFile file) {
    String picUrl="";

    if (!file.isEmpty()) {
      File fileToCreate = new File(upload);
      if (!fileToCreate.exists()) {
        fileToCreate.mkdir();
      }
      picUrl = System.currentTimeMillis() + "_" + file.getOriginalFilename();
      try {
        file.transferTo(new File(upload + File.separator + picUrl));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return picUrl;
  }

}
