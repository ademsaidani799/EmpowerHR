package com.project.EmpowerHR.DTOs;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JobDepartmentDto {
    private int jobID;
    private String jobDept;
    private String name;
    private String description;
    private String salaryRange;
    private List<SalaryDto > salaryDtos;
    private List<PayrollDto > payrollDtos;


}
