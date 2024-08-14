package com.project.EmpowerHR.Entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class SalaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryID;



    private int amount;

    private double annual;

    private double bonus;

    @ManyToOne
    @JoinColumn(name="jobID")
    private JobDepartmentEntity jobDepartment;

    @OneToMany(mappedBy = "salaryEntity")
    private List<PayrollEntity> payrollEntityList=new ArrayList<>();

}