package com.project.EmpowerHR.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class SalaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryID;

    private int jobID;

    private int amount;

    private double annual;

    private double bonus;

    @ManyToOne
    @JoinColumn(name="jobID")
    private JobDepartmentEntity jobDepartment;



    @OneToMany(mappedBy = "payroll")
    private List<PayrollEntity> payrollEntityList;
// Getters and Setters

    public List<PayrollEntity> getPayrollList() {
        return payrollEntityList;
    }

    public void setPayrollList(List<PayrollEntity> payrollEntityList) {
        this.payrollEntityList = payrollEntityList;
    }

    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getAnnual() {
        return annual;
    }

    public void setAnnual(double annual) {
        this.annual = annual;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public JobDepartmentEntity getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(JobDepartmentEntity jobDepartment) {
        this.jobDepartment = jobDepartment;
    }
}