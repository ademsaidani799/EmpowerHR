package com.project.EmpowerHR.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
public class LeaveEnitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @Temporal(TemporalType.DATE)
    private Date date  ;
    private String reason;
    @ManyToOne
    @JoinColumn(name="empID")
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "payroll")
    private List<PayrollEntity> payrollEntityList;

    public List<PayrollEntity> getPayrollList() {
        return payrollEntityList;
    }

    public void setPayrollList(List<PayrollEntity> payrollEntityList) {
        this.payrollEntityList = payrollEntityList;
    }

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


}