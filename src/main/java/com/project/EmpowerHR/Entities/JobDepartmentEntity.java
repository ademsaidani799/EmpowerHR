package com.project.EmpowerHR.Entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class JobDepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobID;

    private String jobDept;
    private String name;
    private String description;
    private String salaryRange;

    @OneToMany(mappedBy = "jobDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryEntity> salaryEntityList=new ArrayList<>();
    @OneToMany(mappedBy = "jobDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayrollEntity> payrollEntityList=new ArrayList<>();

    // Getters and Setters

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobDept() {
        return jobDept;
    }

    public void setJobDept(String jobDept) {
        this.jobDept = jobDept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public List<SalaryEntity> getSalaryEntityList() {
        return salaryEntityList;
    }

    public void setSalaryEntityList(List<SalaryEntity> salaryEntityList) {
        this.salaryEntityList = salaryEntityList;
    }

    public List<PayrollEntity> getPayrollEntityList() {
        return payrollEntityList;
    }

    public void setPayrollEntityList(List<PayrollEntity> payrollEntityList) {
        this.payrollEntityList = payrollEntityList;
    }
}
