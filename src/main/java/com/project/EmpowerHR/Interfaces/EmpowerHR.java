package com.project.EmpowerHR.Interfaces;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;

import java.util.List;

public interface EmpowerHR {
    // Employee CRUD methods
    EmployeeEntity createEmployee(EmployeeDto employee) throws Exception;
    EmployeeEntity getEmployeeById(Long id) throws Exception;
    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity updateEmployee(Long id, EmployeeDto employee) throws Exception;
    void deleteEmployee(Long id) throws Exception;

    // JobDepartment CRUD methods
    JobDepartmentEntity createJobDepartment(JobDepartmentDto jobDepartment) throws Exception;
    JobDepartmentEntity getJobDepartmentById(Long id) throws Exception;
    List<JobDepartmentEntity> getAllJobDepartments();
    JobDepartmentEntity updateJobDepartment(Long id, JobDepartmentDto jobDepartment) throws Exception;
    void deleteJobDepartment(Long id) throws Exception;

    // Leave CRUD methods
    LeaveEntity createLeave(LeaveDto leave) throws Exception;
    LeaveEntity getLeaveById(Long id) throws Exception;
    List<LeaveEntity> getAllLeaves();
    LeaveEntity updateLeave(Long id, LeaveDto leave) throws Exception;
    void deleteLeave(Long id) throws Exception;

    // Payroll CRUD methods
    PayrollEntity getPayrollById(Long id) throws Exception;
    List<PayrollEntity> getAllPayrolls();
    PayrollEntity updatePayroll(Long id, PayrollDto payroll) throws Exception;
    void deletePayroll(Long id) throws Exception;

    // Qualification CRUD methods
    QualificationEntity getQualificationById(Long id) throws Exception;
    List<QualificationEntity> getAllQualifications();
    QualificationEntity updateQualification(Long id, QualificationDto qualification) throws Exception;
    void deleteQualification(Long id) throws Exception;

    // User CRUD methods
    UserEntity createUser(UserDto user) throws Exception;
    UserEntity getUserById(Long id) throws Exception;
    List<UserEntity> getAllUsers();
    UserEntity updateUser(Long id, UserDto user) throws Exception;
    void deleteUser(Long id) throws Exception;

    // Salary CRUD methods
    SalaryEntity createSalary(SalaryDto salary) throws Exception;
    SalaryEntity getSalaryById(Long id) throws Exception;
    List<SalaryEntity> getAllSalaries();
    SalaryEntity updateSalary(Long id, SalaryDto salary) throws Exception;
    void deleteSalary(Long id) throws Exception;
}
