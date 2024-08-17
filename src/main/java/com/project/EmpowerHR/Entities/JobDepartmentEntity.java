package com.project.EmpowerHR.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class JobDepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobID;

    private String jobDept;
    private String name;
    private String description;
    private String salaryRange;
    @OneToMany(mappedBy = "jobDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryEntity> salaryEntityList=new ArrayList<>();
    @OneToMany(mappedBy = "jobDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayrollEntity> payrollEntityList=new ArrayList<>();
}
