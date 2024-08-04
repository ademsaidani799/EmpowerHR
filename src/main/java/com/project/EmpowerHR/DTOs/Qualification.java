package com.project.EmpowerHR.DTOs;

import java.util.Date;

public class Qualification {

    private int qualID;
    private int empID;
    private String position;
    private String requirements;
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