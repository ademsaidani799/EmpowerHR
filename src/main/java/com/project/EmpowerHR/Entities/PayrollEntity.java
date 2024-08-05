package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
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


    @ManyToOne
    @JoinColumn(name="empID")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    @JoinColumn(name="salaryID")
    private SalaryEntity salaryEntity;

    @ManyToOne
    @JoinColumn(name="leaveId")
    private LeaveEntity leave;

}