package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;

    private String firstName;

    private String lastName;

    private int gender;

    private int age;

    private int contactAddress;

    private String email;

    private String password;

    @OneToMany(mappedBy = "qualification")
    private List<QualificationEntity> qualificationList=new ArrayList<>();

    @OneToMany(mappedBy = "payroll")
    private List<PayrollEntity> payrollEntities=new ArrayList<>();
    @OneToMany(mappedBy = "leave")
    private List<LeaveEntity> leaves=new ArrayList<>();

}