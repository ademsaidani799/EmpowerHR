package com.project.EmpowerHR.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class JobDepartmentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobID;

    private String jobDept;

    private String name;

    private String description;

    public List<SalaryEntity> getSalaryList() {
        return salaryEntityList;
    }

    public void setSalaryList(List<SalaryEntity> salaryEntityList) {
        this.salaryEntityList = salaryEntityList;
    }

    public List<PayrollEntity> getPayrollList() {
        return payrollEntityList;
    }

    public void setPayrollList(List<PayrollEntity> payrollEntityList) {
        this.payrollEntityList = payrollEntityList;
    }

    private String salaryRange;

    @OneToMany(mappedBy = "salary")
    private List<SalaryEntity> salaryEntityList;

    @OneToMany(mappedBy = "payroll")
    private List<PayrollEntity> payrollEntityList;
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
}