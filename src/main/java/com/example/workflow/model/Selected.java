package com.example.workflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "selected")
public class Selected {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
private String status;
    @OneToOne
    private Customer customer;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "customer", fetch = FetchType.EAGER)
//    private Set<Workflow> workflow = new HashSet<>();
}
