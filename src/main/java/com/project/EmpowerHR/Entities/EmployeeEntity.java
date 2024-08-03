package com.project.EmpowerHR.Entities;
import jakarta.persistence.*;

import java.util.List;

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
    private List<QualificationEntity> qualificationList;
    // Getters and Setters

    @OneToMany(mappedBy = "payroll")
    private List<PayrollEntity> payrollEntities;

    @OneToMany(mappedBy = "leave")
    private List<LeaveEnitiy> leaves;

    public List<QualificationEntity> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<QualificationEntity> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<PayrollEntity> getPayrolls() {
        return payrollEntities;
    }

    public void setPayrolls(List<PayrollEntity> payrollEntities) {
        this.payrollEntities = payrollEntities;
    }

    public List<LeaveEnitiy> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveEnitiy> leaves) {
        this.leaves = leaves;
    }

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
}