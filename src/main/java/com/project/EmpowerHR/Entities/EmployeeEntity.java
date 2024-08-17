package com.project.EmpowerHR.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long empID;

    private String firstName;

    private String lastName;

    private int gender;

    private int age;

    private int contactAddress;

    private String email;

    private String password;
    @OneToMany(mappedBy = "employee")
    private List<QualificationEntity> qualificationList=new ArrayList<>();
    @OneToMany(mappedBy = "employee")
    private List<PayrollEntity> payrollEntities=new ArrayList<>();
    @OneToMany(mappedBy = "employee")
    private List<LeaveEntity> leaves=new ArrayList<>();

}