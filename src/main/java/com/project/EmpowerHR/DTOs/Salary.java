package com.project.EmpowerHR.DTOs;

import com.project.EmpowerHR.Entities.PayrollEntity;

import java.util.List;

public class Salary {

    private int salaryID;
    private int jobID;
    private int amount;
    private double annual;
    private double bonus;
    private List<Payroll> payrollEntityList;

    // Getters and Setters

    public List<Payroll> getPayrollEntityList() {
        return payrollEntityList;
    }

    public void setPayrollEntityList(List<Payroll> payrollEntityList) {
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
    }}
