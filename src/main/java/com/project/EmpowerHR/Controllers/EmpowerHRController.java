package com.project.EmpowerHR.Controllers;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Interfaces.EmpowerHR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpowerHRController {

    @Autowired
    private EmpowerHR empowerHRService;

    // Employee Endpoints
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employee) throws Exception {
        EmployeeDto createdEmployee = empowerHRService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws Exception {
        EmployeeDto employee = empowerHRService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = empowerHRService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee) throws Exception {
        EmployeeDto updatedEmployee = empowerHRService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws Exception {
        empowerHRService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // JobDepartment Endpoints
    @PostMapping("/job-departments")
    public ResponseEntity<JobDepartmentDto> createJobDepartment(@RequestBody JobDepartmentDto jobDepartment) throws Exception {
        JobDepartmentDto createdJobDepartment = empowerHRService.createJobDepartment(jobDepartment);
        return ResponseEntity.ok(createdJobDepartment);
    }

    @GetMapping("/job-departments/{id}")
    public ResponseEntity<JobDepartmentDto> getJobDepartmentById(@PathVariable Long id) throws Exception {
        JobDepartmentDto jobDepartment = empowerHRService.getJobDepartmentById(id);
        return ResponseEntity.ok(jobDepartment);
    }

    @GetMapping("/job-departments")
    public ResponseEntity<List<JobDepartmentDto>> getAllJobDepartments() {
        List<JobDepartmentDto> jobDepartments = empowerHRService.getAllJobDepartments();
        return ResponseEntity.ok(jobDepartments);
    }

    @PutMapping("/job-departments/{id}")
    public ResponseEntity<JobDepartmentDto> updateJobDepartment(@PathVariable Long id, @RequestBody JobDepartmentDto jobDepartment) throws Exception {
        JobDepartmentDto updatedJobDepartment = empowerHRService.updateJobDepartment(id, jobDepartment);
        return ResponseEntity.ok(updatedJobDepartment);
    }

    @DeleteMapping("/job-departments/{id}")
    public ResponseEntity<Void> deleteJobDepartment(@PathVariable Long id) throws Exception {
        empowerHRService.deleteJobDepartment(id);
        return ResponseEntity.noContent().build();
    }

    // Leave Endpoints
    @PostMapping("/leaves")
    public ResponseEntity<LeaveDto> createLeave(@RequestBody LeaveDto leave) throws Exception {
        LeaveDto createdLeave = empowerHRService.createLeave(leave);
        return ResponseEntity.ok(createdLeave);
    }

    @GetMapping("/leaves/{id}")
    public ResponseEntity<LeaveDto> getLeaveById(@PathVariable Long id) throws Exception {
        LeaveDto leave = empowerHRService.getLeaveById(id);
        return ResponseEntity.ok(leave);
    }

    @GetMapping("/leaves")
    public ResponseEntity<List<LeaveDto>> getAllLeaves() {
        List<LeaveDto> leaves = empowerHRService.getAllLeaves();
        return ResponseEntity.ok(leaves);
    }

    @PutMapping("/leaves/{id}")
    public ResponseEntity<LeaveDto> updateLeave(@PathVariable Long id, @RequestBody LeaveDto leave) throws Exception {
        LeaveDto updatedLeave = empowerHRService.updateLeave(id, leave);
        return ResponseEntity.ok(updatedLeave);
    }

    @DeleteMapping("/leaves/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) throws Exception {
        empowerHRService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }

    // Payroll Endpoints
    @GetMapping("/payrolls/{id}")
    public ResponseEntity<PayrollDto> getPayrollById(@PathVariable Long id) throws Exception {
        PayrollDto payroll = empowerHRService.getPayrollById(id);
        return ResponseEntity.ok(payroll);
    }

    @GetMapping("/payrolls")
    public ResponseEntity<List<PayrollDto>> getAllPayrolls() {
        List<PayrollDto> payrolls = empowerHRService.getAllPayrolls();
        return ResponseEntity.ok(payrolls);
    }

    @PutMapping("/payrolls/{id}")
    public ResponseEntity<PayrollDto> updatePayroll(@PathVariable Long id, @RequestBody PayrollDto payroll) throws Exception {
        PayrollDto updatedPayroll = empowerHRService.updatePayroll(id, payroll);
        return ResponseEntity.ok(updatedPayroll);
    }
    @PostMapping("/payrolls")
    public ResponseEntity<PayrollDto> createPayroll(@RequestBody PayrollDto payroll) throws Exception {
        PayrollDto updatedPayroll = empowerHRService.createPayroll(payroll);
        return ResponseEntity.ok(updatedPayroll);
    }

    @DeleteMapping("/payrolls/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) throws Exception {
        empowerHRService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

    // Qualification Endpoints
    @GetMapping("/qualifications/{id}")
    public ResponseEntity<QualificationDto> getQualificationById(@PathVariable Long id) throws Exception {
        QualificationDto qualification = empowerHRService.getQualificationById(id);
        return ResponseEntity.ok(qualification);
    }

    @GetMapping("/qualifications")
    public ResponseEntity<List<QualificationDto>> getAllQualifications() {
        List<QualificationDto> qualifications = empowerHRService.getAllQualifications();
        return ResponseEntity.ok(qualifications);
    }

    @PutMapping("/qualifications/{id}")
    public ResponseEntity<QualificationDto> updateQualification(@PathVariable Long id, @RequestBody QualificationDto qualification) throws Exception {
        QualificationDto updatedQualification = empowerHRService.updateQualification(id, qualification);
        return ResponseEntity.ok(updatedQualification);
    }
    @PostMapping("/qualifications")
    public ResponseEntity<QualificationDto> createQualification(@RequestBody QualificationDto qualification) throws Exception {
        QualificationDto createdQualification = empowerHRService.createQualification(qualification);
        return ResponseEntity.ok(createdQualification) ;
    }

    @DeleteMapping("/qualifications/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) throws Exception {
        empowerHRService.deleteQualification(id);
        return ResponseEntity.noContent().build();
    }

    // User Endpoints
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws Exception {
        UserDto createdUser = empowerHRService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws Exception {
        UserDto user = empowerHRService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = empowerHRService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) throws Exception {
        UserDto updatedUser = empowerHRService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws Exception {
        empowerHRService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Salary Endpoints
    @PostMapping("/salaries")
    public ResponseEntity<SalaryDto> createSalary(@RequestBody SalaryDto salary) throws Exception {
        SalaryDto createdSalary = empowerHRService.createSalary(salary);
        return ResponseEntity.ok(createdSalary);
    }

    @GetMapping("/salaries/{id}")
    public ResponseEntity<SalaryDto> getSalaryById(@PathVariable Long id) throws Exception {
        SalaryDto salary = empowerHRService.getSalaryById(id);
        return ResponseEntity.ok(salary);
    }

    @GetMapping("/salaries")
    public ResponseEntity<List<SalaryDto>> getAllSalaries() {
        List<SalaryDto> salaries = empowerHRService.getAllSalaries();
        return ResponseEntity.ok(salaries);
    }

    @PutMapping("/salaries/{id}")
    public ResponseEntity<SalaryDto> updateSalary(@PathVariable Long id, @RequestBody SalaryDto salary) throws Exception {
        SalaryDto updatedSalary = empowerHRService.updateSalary(id, salary);
        return ResponseEntity.ok(updatedSalary);
    }

    @DeleteMapping("/salaries/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) throws Exception {
        empowerHRService.deleteSalary(id);
        return ResponseEntity.noContent().build();
    }
}
