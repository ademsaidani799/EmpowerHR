package com.project.EmpowerHR.DTOs;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmployeeDto {

    private Long empID;
    private String firstName;
    private String lastName;
    private int gender;
    private int age;
    private int contactAddress;
    private String email;
    private String password;
    private List<QualificationDto>  qualificationList;
    private List<PayrollDto > payrollEntities;

    private List<LeaveDto > leaves;
}
