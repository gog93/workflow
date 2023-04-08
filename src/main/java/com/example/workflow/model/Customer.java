package com.example.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Convert(converter = StringListConverter.class)
//    private List<String> phone;

    @Column(name = "email", unique = true)
    private String email;

    //    private Long createdAt;
//    private Long updatedAt;
    @JsonIgnore
    @ManyToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Workflow> workflow = new HashSet<>();
}
