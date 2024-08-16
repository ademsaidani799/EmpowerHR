package com.project.EmpowerHR.DTOs;

import java.util.Date;

import com.project.EmpowerHR.Entities.EmployeeEntity;
import com.project.EmpowerHR.Entities.JobDepartmentEntity;
import com.project.EmpowerHR.Entities.LeaveEntity;
import com.project.EmpowerHR.Entities.SalaryEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PayrollDto {

    private Long payrollID;


    private Date date;

    private String report;

    private double totalAmount;


    private JobDepartmentDto jobDepartmentDto;


    private EmployeeDto  employeeDto ;

    private SalaryDto salaryDto ;

    private LeaveDto  leaveDto;

}
