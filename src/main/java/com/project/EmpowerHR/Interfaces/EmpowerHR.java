package com.project.EmpowerHR.Interfaces;

import com.project.EmpowerHR.DTOs.*;

import java.util.List;

public interface EmpowerHR {
    // Employee CRUD methods
    EmployeeDto createEmployee(EmployeeDto employee) throws Exception;
    EmployeeDto getEmployeeById(Long id) throws Exception;
    public List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long id, EmployeeDto employee) throws Exception;
    void deleteEmployee(Long id) throws Exception;

    // JobDepartment CRUD methods
    JobDepartmentDto createJobDepartment(JobDepartmentDto jobDepartment) throws Exception;
    JobDepartmentDto getJobDepartmentById(Long id) throws Exception;
    List<JobDepartmentDto> getAllJobDepartments();
    JobDepartmentDto updateJobDepartment(Long id, JobDepartmentDto jobDepartment) throws Exception;
    void deleteJobDepartment(Long id) throws Exception;

    // Leave CRUD methods
    LeaveDto createLeave(LeaveDto leave) throws Exception;
    LeaveDto getLeaveById(Long id) throws Exception;
    List<LeaveDto> getAllLeaves();
    LeaveDto updateLeave(Long id, LeaveDto leave) throws Exception;
    void deleteLeave(Long id) throws Exception;

    // Payroll CRUD methods
    PayrollDto getPayrollById(Long id) throws Exception;
    List<PayrollDto> getAllPayrolls();
    PayrollDto updatePayroll(Long id, PayrollDto payroll) throws Exception;
    void deletePayroll(Long id) throws Exception;

    // Qualification CRUD methods
    QualificationDto getQualificationById(Long id) throws Exception;
    List<QualificationDto> getAllQualifications();
    QualificationDto updateQualification(Long id, QualificationDto qualification) throws Exception;
    void deleteQualification(Long id) throws Exception;

    // User CRUD methods
    UserDto createUser(UserDto user) throws Exception;
    UserDto getUserById(Long id) throws Exception;
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto user) throws Exception;
    void deleteUser(Long id) throws Exception;

    // Salary CRUD methods
    SalaryDto createSalary(SalaryDto salary) throws Exception;
    SalaryDto getSalaryById(Long id) throws Exception;
    List<SalaryDto> getAllSalaries();
    SalaryDto updateSalary(Long id, SalaryDto salary) throws Exception;
    void deleteSalary(Long id) throws Exception;

    QualificationDto createQualification(QualificationDto qualification) throws Exception;

    PayrollDto createPayroll(PayrollDto payroll) throws Exception;
}
