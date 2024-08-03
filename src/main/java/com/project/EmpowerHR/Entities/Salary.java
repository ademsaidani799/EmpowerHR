package com.project.EmpowerHR.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryID;

    private int jobID;

    private int amount;

    private double annual;

    private double bonus;

    @ManyToOne
    @JoinColumn(name="jobID")
    private JobDepartment jobDepartment;



    @OneToMany(mappedBy = "payroll")
    private List<Payroll> payrollList;
// Getters and Setters

    public List<Payroll> getPayrollList() {
        return payrollList;
    }

    public void setPayrollList(List<Payroll> payrollList) {
        this.payrollList = payrollList;
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
    public JobDepartment getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(JobDepartment jobDepartment) {
        this.jobDepartment = jobDepartment;
    }
}