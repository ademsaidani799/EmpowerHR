package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class QualificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qualID;

    private int empID;

    private String position;

    private String requirements;

    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @ManyToOne
    @JoinColumn(name="empID")
    private EmployeeEntity employeeEntity;



}