package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qualID;

    private int empID;

    private String position;

    private String requirements;

    @Temporal(TemporalType.DATE)
    private Date dateIn;

    // Getters and Setters

    public int getQualID() {
        return qualID;
    }

    public void setQualID(int qualID) {
        this.qualID = qualID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }
}