package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollID;

    private int empID;

    private int jobID;

    private int salaryID;

    private int leaveID;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String report;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name="jobID")
    private JobDepartment jobDepartment;

    public JobDepartment getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(JobDepartment jobDepartment) {
        this.jobDepartment = jobDepartment;
    }

    @ManyToOne
    @JoinColumn(name="empID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="salaryID")
    private Salary salary;

    @ManyToOne
    @JoinColumn(name="leaveId")
    private Leave leave;

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
// Getters and Setters

    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
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