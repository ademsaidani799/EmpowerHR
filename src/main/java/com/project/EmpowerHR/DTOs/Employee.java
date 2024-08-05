package com.project.EmpowerHR.DTOs;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Employee {

    private int empID;
    private String firstName;
    private String lastName;
    private int gender;
    private int age;
    private int contactAddress;
    private String email;
    private String password;
    private List<Qualification> qualificationList;
    private List<Payroll> payrollEntities;
    private List<Leave> leaves;
}
