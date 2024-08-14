package com.project.EmpowerHR.Interfaces;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;

import java.util.List;

public interface EmpowerHR {
    // Employee CRUD methods
    void createEmployee(EmployeeDto  employee);
    EmployeeEntity  getEmployeeById(Long id);
    List<EmployeeEntity > getAllEmployees();
    public EmployeeEntity  updateEmployee(EmployeeDto  employee, long id);     void deleteEmployee(Long id);

    // JobDepartment CRUD methods
    JobDepartmentEntity createJobDepartment(JobDepartmentDto  jobDepartment);
    JobDepartmentEntity  getJobDepartmentById(Long id);
    List<JobDepartmentEntity > getAllJobDepartments();
    public JobDepartmentEntity  updateJobDepartment(JobDepartmentDto  jobDepartment,Long id);

    void deleteJobDepartment(Long id);

    // Leave CRUD methods
    LeaveEntity createLeave(LeaveDto  leave);
    LeaveEntity getLeaveById(Long id);
    List<LeaveEntity> getAllLeaves();
    LeaveEntity updateLeave(LeaveDto  leave,long id);
    void deleteLeave(Long id);

    // Payroll CRUD methods
    PayrollEntity getPayrollById(Long id);
    List<PayrollEntity> getAllPayrolls();
    PayrollEntity updatePayroll(PayrollDto  payroll,long id);
    void deletePayroll(Long id);

    // Qualification CRUD methods
    QualificationEntity getQualificationById(Long id);
    List<QualificationEntity> getAllQualifications();
    QualificationEntity updateQualification(QualificationDto  qualification,long id);
    void deleteQualification(Long id);

    // User CRUD methods
    UserEntity createUser(UserDto  user);
    UserEntity getUserById(Long id);
    List<UserEntity> getAllUsers();
    UserEntity updateUser(UserDto  user,long id);
    void deleteUser(Long id);

    // Salary CRUD methods
    SalaryEntity createSalary(SalaryDto  salary);
    SalaryEntity getSalaryById(Long id);
    List<SalaryEntity> getAllSalaries();
    SalaryEntity updateSalary(SalaryDto  salary,long id);
    void deleteSalary(Long id);
}
