package com.project.EmpowerHR.DTOs;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LeaveDto {
    private int leaveId;
    private Date date;
    private String reason;
private int empID;
private List<PayrollDto > payrollDtos;



}