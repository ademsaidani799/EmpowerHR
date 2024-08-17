package com.project.EmpowerHR.DTOs;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JobDepartmentDto {
    private Long jobID;
    private String jobDept;
    private String name;
    private String description;
    private String salaryRange;
    private List<SalaryDto > salaryEntityList;
    private List<PayrollDto > payrollEntityList;


}
