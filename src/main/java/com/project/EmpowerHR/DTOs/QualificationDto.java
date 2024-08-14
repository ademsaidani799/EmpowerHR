package com.project.EmpowerHR.DTOs;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class QualificationDto  {

    private int qualID;
    private EmployeeDto employee;
    private String position;
    private String requirements;
    private Date dateIn;


}
