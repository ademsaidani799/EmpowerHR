package com.project.EmpowerHR.Interfaces;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;

import java.util.List;

public interface EmpowerHR {
    // Employee CRUD methods
    EmployeeEntity createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    EmployeeEntity updateEmployee(Employee employee);
    void deleteEmployee(Long id);

    // JobDepartment CRUD methods
    JobDepartmentEntity createJobDepartment(JobDepartment jobDepartment);
    JobDepartmentEntity getJobDepartmentById(Long id);
    List<JobDepartmentEntity> getAllJobDepartments();
    JobDepartmentEntity updateJobDepartment(JobDepartment jobDepartment);
    void deleteJobDepartment(Long id);

    // Leave CRUD methods
    LeaveEntity createLeave(Leave leave);
    LeaveEntity getLeaveById(Long id);
    List<LeaveEntity> getAllLeaves();
    LeaveEntity updateLeave(Leave leave);
    void deleteLeave(Long id);

    // Payroll CRUD methods
    PayrollEntity getPayrollById(Long id);
    List<PayrollEntity> getAllPayrolls();
    PayrollEntity updatePayroll(Payroll payroll);
    void deletePayroll(Long id);

    // Qualification CRUD methods
    QualificationEntity getQualificationById(Long id);
    List<QualificationEntity> getAllQualifications();
    QualificationEntity updateQualification(Qualification qualification);
    void deleteQualification(Long id);

    // User CRUD methods
    UserEntity createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long id);

    // Salary CRUD methods
    SalaryEntity createSalary(Salary salary);
    SalaryEntity getSalaryById(Long id);
    List<SalaryEntity> getAllSalaries();
    SalaryEntity updateSalary(Salary salary);
    void deleteSalary(Long id);
}
