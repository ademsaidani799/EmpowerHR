package com.project.EmpowerHR.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class LeaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "empID")
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "leave", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayrollEntity> payrollEntityList=new ArrayList<>();

    // Getters and Setters

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public EmployeeEntity getEmployee() {
        return employeeEntity;
    }

    public void setEmployee(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public List<PayrollEntity> getPayrollEntityList() {
        return payrollEntityList;
    }

    public void setPayrollEntityList(List<PayrollEntity> payrollEntityList) {
        this.payrollEntityList = payrollEntityList;
    }
}
