package com.project.EmpowerHR.DTOs;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Leave {
    private int leaveId;
    private Date date;
    private String reason;
    private Employee employee;
    private List<Payroll> payrollList;


}
