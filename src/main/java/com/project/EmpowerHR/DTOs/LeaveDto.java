package com.project.EmpowerHR.DTOs;

import java.util.Date;
import java.util.List;

import com.project.EmpowerHR.Entities.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LeaveDto {
    private Long leaveId;
    private Date date;
    private String reason;
    private EmployeeDto employeeDto;

private List<PayrollDto > payrollDtos;



}
