package com.project.EmpowerHR.DTOs;

import java.util.List;

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

    // Getters and Setters
    // Existing getters and setters

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(int contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<Payroll> getPayrollEntities() {
        return payrollEntities;
    }

    public void setPayrollEntities(List<Payroll> payrollEntities) {
        this.payrollEntities = payrollEntities;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }
}
