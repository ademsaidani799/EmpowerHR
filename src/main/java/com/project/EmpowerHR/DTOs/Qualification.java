package com.project.EmpowerHR.DTOs;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Qualification {

    private int qualID;
    private int empID;
    private String position;
    private String requirements;
    private Date dateIn;


}
