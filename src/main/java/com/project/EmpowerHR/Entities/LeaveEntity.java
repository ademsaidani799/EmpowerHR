package com.project.EmpowerHR.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class LeaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "empID")
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "leave", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayrollEntity> payrollEntityList=new ArrayList<>();


}
