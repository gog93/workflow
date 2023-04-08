package com.example.workflow.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "workflows")
public class Workflow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Workflow name can not be null")
    private String name;
    @NotNull(message = "Workflow name can not be null")

    private String description;

    //    @Column(name = "created_at", nullable = false, updatable = false)
//    private Long createdAt;

    private String uploadFile;
    //
    @Column(insertable = false, updatable = false)
    private Long updatedAt;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "workflow_costumer_mapping",
            joinColumns = @JoinColumn(name = "workflow_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customer;
}