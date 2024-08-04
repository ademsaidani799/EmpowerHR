package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PayrollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollID;


    @Temporal(TemporalType.DATE)
    private Date date;

    private String report;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name="jobID")
    private JobDepartmentEntity jobDepartment;

    public JobDepartmentEntity getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(JobDepartmentEntity jobDepartment) {
        this.jobDepartment = jobDepartment;
    }

    @ManyToOne
    @JoinColumn(name="empID")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    @JoinColumn(name="salaryID")
    private SalaryEntity salaryEntity;

    @ManyToOne
    @JoinColumn(name="leaveId")
    private LeaveEntity leave;

    public LeaveEntity getLeave() {
        return leave;
    }

    public void setLeave(LeaveEntity leave) {
        this.leave = leave;
    }

    public EmployeeEntity getEmployee() {
        return employeeEntity;
    }

    public SalaryEntity getSalary() {
        return salaryEntity;
    }

    public void setSalary(SalaryEntity salaryEntity) {
        this.salaryEntity = salaryEntity;
    }

    public void setEmployee(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }
// Getters and Setters

    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public SalaryEntity getSalaryEntity() {
        return salaryEntity;
    }

    public void setSalaryEntity(SalaryEntity salaryEntity) {
        this.salaryEntity = salaryEntity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}