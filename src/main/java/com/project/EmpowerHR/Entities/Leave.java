package com.project.EmpowerHR.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @Temporal(TemporalType.DATE)
    private Date date  ;
    private String reason;
    @ManyToOne
    @JoinColumn(name="empID")
    private Employee employee;

    @OneToMany(mappedBy = "payroll")
    private List<Payroll> payrollList;

    public List<Payroll> getPayrollList() {
        return payrollList;
    }

    public void setPayrollList(List<Payroll> payrollList) {
        this.payrollList = payrollList;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}