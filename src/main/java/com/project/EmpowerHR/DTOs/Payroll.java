package com.project.EmpowerHR.DTOs;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Payroll {

    private int payrollID;
    private int empID;
    private int jobID;
    private int salaryID;
    private int leaveID;
    private Date date;
    private String report;
    private double totalAmount;


}
