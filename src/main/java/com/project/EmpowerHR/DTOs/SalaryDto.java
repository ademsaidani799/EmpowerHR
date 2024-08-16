package com.project.EmpowerHR.DTOs;

import com.project.EmpowerHR.Entities.PayrollEntity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SalaryDto  {

    private Long salaryID;
    private JobDepartmentDto jobDepartmentDto;
    private int amount;
    private double annual;
    private double bonus;
    private List<PayrollDto>  payrollDtos;

}
