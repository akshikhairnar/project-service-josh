package com.projectsservice.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Projects")
public class Project {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(unique = true)
    private String projectName;

    // @JsonIgnore
    private LocalDate projectAllocationDate;

    // @JsonIgnore
  //  private Long employeeId;


}
