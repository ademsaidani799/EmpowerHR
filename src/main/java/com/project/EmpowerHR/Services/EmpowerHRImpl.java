package com.project.EmpowerHR.Services;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;
import com.project.EmpowerHR.Interfaces.EmpowerHR;
import com.project.EmpowerHR.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmpowerHRImpl implements EmpowerHR {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private JobDepartementRepository jobDepartmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    //implemetntationof Create
    public EmployeeEntity createEmployee(EmployeeDto employeeDto) throws Exception {
        // Check if an employee with the same email already exists
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            // Email is already taken, handle accordingly (throw an exception, return an error response, etc.)
            throw new Exception("Employee with this email already exists");
        }

        // Create a new EmployeeEntity
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setAge(employeeDto.getAge());
        employeeEntity.setContactAddress(employeeDto.getContactAddress());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setPassword(employeeDto.getPassword());

        // Set qualifications, payrolls, and leaves if needed
        // Note: Ensure to handle setting these lists correctly as per your entity design

        // Save the new employee
        return employeeRepository.saveAndFlush(employeeEntity);
    }
    public JobDepartmentEntity createJobDepartment(JobDepartmentDto jobDepartmentDto) {
        // Create a new JobDepartmentEntity
        JobDepartmentEntity jobDepartmentEntity = new JobDepartmentEntity();
        jobDepartmentEntity.setJobDept(jobDepartmentDto.getJobDept());
        jobDepartmentEntity.setName(jobDepartmentDto.getName());
        jobDepartmentEntity.setDescription(jobDepartmentDto.getDescription());
        jobDepartmentEntity.setSalaryRange(jobDepartmentDto.getSalaryRange());


        // Save the new job department
        return jobDepartmentRepository.saveAndFlush(jobDepartmentEntity);
    }

    public SalaryEntity createSalary(SalaryDto salaryDto) throws Exception {
        try {
            // Retrieve JobDepartmentEntity
            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(salaryDto.getJobDepartmentDto().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            // Create and populate SalaryEntity
            SalaryEntity salaryEntity = new SalaryEntity();
            salaryEntity.setAmount(salaryDto.getAmount());
            salaryEntity.setAnnual(salaryDto.getAnnual());
            salaryEntity.setBonus(salaryDto.getBonus());
            salaryEntity.setJobDepartment(jobDepartment);
            // Check if the job department already has a list of salaries
            List<SalaryEntity> salaryEntities = jobDepartment.getSalaryEntityList();
            if (salaryEntities == null) {
                salaryEntities = new ArrayList<>();
            }

            // Add the new salary to the existing list of salaries
            salaryEntities.add(salaryEntity);

            // Update the job department's salary list
            jobDepartment.setSalaryEntityList(salaryEntities);
            jobDepartmentRepository.saveAndFlush(jobDepartment);


            // Save the new salary
            return salaryRepository.saveAndFlush(salaryEntity);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating salary: " + e.getMessage());
        }
    }
    public QualificationEntity createQualification(QualificationDto qualificationDto) throws Exception {
        try {
            // Retrieve the EmployeeEntity
            EmployeeEntity employee = employeeRepository.findById(qualificationDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            // Create and populate QualificationEntity
            QualificationEntity qualificationEntity = new QualificationEntity();
            qualificationEntity.setPosition(qualificationDto.getPosition());
            qualificationEntity.setRequirements(qualificationDto.getRequirements());
            qualificationEntity.setDateIn(qualificationDto.getDateIn());
            qualificationEntity.setEmployeeEntity(employee);

            // Check if the employee already has a list of qualifications
            List<QualificationEntity> qualifications = employee.getQualificationList();
            if (qualifications == null) {
                qualifications = new ArrayList<>();
            }

            // Add the new qualification to the existing list
            qualifications.add(qualificationEntity);

            // Update the employee's qualification list
            employee.setQualificationList(qualifications);
            employeeRepository.saveAndFlush(employee);

            // Save and flush the new qualification entity
            return qualificationRepository.saveAndFlush(qualificationEntity);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating qualification: " + e.getMessage());
        }
    }
    public LeaveEntity createLeave(LeaveDto leaveDto) throws Exception {
        try {
            // Retrieve the EmployeeEntity
            EmployeeEntity employee = employeeRepository.findById(leaveDto.getEmployeeDto().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            // Create and populate LeaveEntity
            LeaveEntity leaveEntity = new LeaveEntity();
            leaveEntity.setDate(leaveDto.getDate());
            leaveEntity.setReason(leaveDto.getReason());
            leaveEntity.setEmployeeEntity(employee);


            // Check if the employee already has a list of leaves
            List<LeaveEntity> leaves = employee.getLeaves();
            if (leaves == null) {
                leaves = new ArrayList<>();
            }

            // Add the new leave to the existing list of leaves
            leaves.add(leaveEntity);

            // Update the employee's leave list
            employee.setLeaves(leaves);
            employeeRepository.saveAndFlush(employee);

            // Save and flush the new leave entity
            return leaveRepository.saveAndFlush(leaveEntity);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating leave: " + e.getMessage());
        }
    }
    public PayrollEntity createPayroll(PayrollDto payrollDto) throws Exception {
        try {
            // Retrieve the EmployeeEntity
            EmployeeEntity employee = employeeRepository.findById(payrollDto.getEmployeeDto().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            // Retrieve the JobDepartmentEntity
            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(payrollDto.getJobDepartmentDto().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            // Retrieve the SalaryEntity
            SalaryEntity salary = salaryRepository.findById(payrollDto.getSalaryDto().getSalaryID())
                    .orElseThrow(() -> new Exception("Salary not found"));

            // Retrieve the LeaveEntity
            LeaveEntity leave = leaveRepository.findById(payrollDto.getLeaveDto().getLeaveId())
                    .orElseThrow(() -> new Exception("Leave not found"));

            // Create and populate PayrollEntity
            PayrollEntity payrollEntity = new PayrollEntity();
            payrollEntity.setDate(payrollDto.getDate());
            payrollEntity.setReport(payrollDto.getReport());
            payrollEntity.setTotalAmount(payrollDto.getTotalAmount());
            payrollEntity.setEmployeeEntity(employee);
            payrollEntity.setJobDepartment(jobDepartment);
            payrollEntity.setSalaryEntity(salary);
            payrollEntity.setLeave(leave);

            // Handle PayrollEntity addition to employee's payroll list
            List<PayrollEntity> payrollEntities = employee.getPayrollEntities();
            if (payrollEntities == null) {
                payrollEntities = new ArrayList<>();
            }
            payrollEntities.add(payrollEntity);
            employee.setPayrollEntities(payrollEntities);

            // Handle PayrollEntity addition to job department's payroll list
            List<PayrollEntity> jobDepartmentPayrollEntities = jobDepartment.getPayrollEntityList();
            if (jobDepartmentPayrollEntities == null) {
                jobDepartmentPayrollEntities = new ArrayList<>();
            }
            jobDepartmentPayrollEntities.add(payrollEntity);
            jobDepartment.setPayrollEntityList(jobDepartmentPayrollEntities);

            // Handle PayrollEntity addition to salary's payroll list
            List<PayrollEntity> salaryPayrollEntities = salary.getPayrollEntityList();
            if (salaryPayrollEntities == null) {
                salaryPayrollEntities = new ArrayList<>();
            }
            salaryPayrollEntities.add(payrollEntity);
            salary.setPayrollEntityList(salaryPayrollEntities);

            // Handle PayrollEntity addition to leave's payroll list
            List<PayrollEntity> leavePayrollEntities = leave.getPayrollEntityList();
            if (leavePayrollEntities == null) {
                leavePayrollEntities = new ArrayList<>();
            }
            leavePayrollEntities.add(payrollEntity);
            leave.setPayrollEntityList(leavePayrollEntities);

            // Save and flush the employee to update payroll association
            employeeRepository.saveAndFlush(employee);

            // Save and flush the job department to update payroll association
            jobDepartmentRepository.saveAndFlush(jobDepartment);

            // Save and flush the salary to update payroll association
            salaryRepository.saveAndFlush(salary);

            // Save and flush the leave to update payroll association
            leaveRepository.saveAndFlush(leave);

            // Save and flush the new payroll entity
            return payrollRepository.saveAndFlush(payrollEntity);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating payroll: " + e.getMessage());
        }
    }

    // Create User
    public UserEntity createUser(UserDto userDto) throws Exception {
        try {
            // Check if the email already exists
            Optional<UserEntity> existingUser = userRepository.findByAdminEmail(userDto.getAdminEmail());
            if (existingUser.isPresent()) {
                throw new Exception("User with this email already exists");
            }

            // Create and populate a new UserEntity
            UserEntity userEntity = new UserEntity();
            userEntity.setFname(userDto.getFname());
            userEntity.setLname(userDto.getLname());
            userEntity.setGender(userDto.getGender());
            userEntity.setAge(userDto.getAge());
            userEntity.setContactAdd(userDto.getContactAdd());
            userEntity.setAdminEmail(userDto.getAdminEmail());
            userEntity.setAdminPass(userDto.getAdminPass());

            // Save the new user
            return userRepository.save(userEntity);
        } catch (Exception e) {
            throw new Exception("Error creating user: " + e.getMessage());
        }
    }
    //implementation of all delete

    // Delete Employee
    public void deleteEmployee(Long id) throws Exception {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
            } else {
                throw new Exception("Employee not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting employee: " + e.getMessage());
        }
    }

    // Delete Job Department
    public void deleteJobDepartment(Long id) throws Exception {
        try {
            if (jobDepartmentRepository.existsById(id)) {
                jobDepartmentRepository.deleteById(id);
            } else {
                throw new Exception("Job Department not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting job department: " + e.getMessage());
        }
    }

    // Delete Salary
    public void deleteSalary(Long id) throws Exception {
        try {
            if (salaryRepository.existsById(id)) {
                salaryRepository.deleteById(id);
            } else {
                throw new Exception("Salary not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting salary: " + e.getMessage());
        }
    }

    // Delete Qualification
    public void deleteQualification(Long id) throws Exception {
        try {
            if (qualificationRepository.existsById(id)) {
                qualificationRepository.deleteById(id);
            } else {
                throw new Exception("Qualification not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting qualification: " + e.getMessage());
        }
    }

    // Delete Leave
    public void deleteLeave(Long id) throws Exception {
        try {
            if (leaveRepository.existsById(id)) {
                leaveRepository.deleteById(id);
            } else {
                throw new Exception("Leave not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting leave: " + e.getMessage());
        }
    }

    // Delete Payroll
    public void deletePayroll(Long id) throws Exception {
        try {
            if (payrollRepository.existsById(id)) {
                payrollRepository.deleteById(id);
            } else {
                throw new Exception("Payroll not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting payroll: " + e.getMessage());
        }
    }
    // Delete User
    public void deleteUser(Long id) throws Exception {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            } else {
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            throw new Exception("Error deleting user: " + e.getMessage());
        }
    }
    // implementation of all get


    // Employee read methods
    public EmployeeEntity getEmployeeById(Long id) throws Exception {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employee not found"));
    }

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // JobDepartment read methods
    public JobDepartmentEntity getJobDepartmentById(Long id) throws Exception {
        return jobDepartmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Job Department not found"));
    }

    public List<JobDepartmentEntity> getAllJobDepartments() {
        return jobDepartmentRepository.findAll();
    }

    // Leave read methods
    public LeaveEntity getLeaveById(Long id) throws Exception {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new Exception("Leave not found"));
    }

    public List<LeaveEntity> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // Payroll read methods
    public PayrollEntity getPayrollById(Long id) throws Exception {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new Exception("Payroll not found"));
    }

    public List<PayrollEntity> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // Qualification read methods
    public QualificationEntity getQualificationById(Long id) throws Exception {
        return qualificationRepository.findById(id)
                .orElseThrow(() -> new Exception("Qualification not found"));
    }

    public List<QualificationEntity> getAllQualifications() {
        return qualificationRepository.findAll();
    }

    // User read methods
    public UserEntity getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Salary read methods
    public SalaryEntity getSalaryById(Long id) throws Exception {
        return salaryRepository.findById(id)
                .orElseThrow(() -> new Exception("Salary not found"));
    }

    public List<SalaryEntity> getAllSalaries() {
        return salaryRepository.findAll();
    }

    //implementation of all update methods


    // Update User
    public UserEntity updateUser(Long id, UserDto userDto) throws Exception {
        try {
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new Exception("User not found"));

            userEntity.setFname(userDto.getFname());
            userEntity.setLname(userDto.getLname());
            userEntity.setGender(userDto.getGender());
            userEntity.setAge(userDto.getAge());
            userEntity.setContactAdd(userDto.getContactAdd());
            userEntity.setAdminEmail(userDto.getAdminEmail());
            userEntity.setAdminPass(userDto.getAdminPass());

            return userRepository.save(userEntity);
        } catch (Exception e) {
            throw new Exception("Error updating user: " + e.getMessage());
        }
    }

    // Update Employee
    public EmployeeEntity updateEmployee(Long id, EmployeeDto employeeDto) throws Exception {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findById(id)
                    .orElseThrow(() -> new Exception("Employee not found"));

            employeeEntity.setFirstName(employeeDto.getFirstName());
            employeeEntity.setLastName(employeeDto.getLastName());
            employeeEntity.setGender(employeeDto.getGender());
            employeeEntity.setAge(employeeDto.getAge());
            employeeEntity.setContactAddress(employeeDto.getContactAddress());
            employeeEntity.setEmail(employeeDto.getEmail());
            employeeEntity.setPassword(employeeDto.getPassword());

            return employeeRepository.save(employeeEntity);
        } catch (Exception e) {
            throw new Exception("Error updating employee: " + e.getMessage());
        }
    }

    // Update Job Department
    public JobDepartmentEntity updateJobDepartment(Long id, JobDepartmentDto jobDepartmentDto) throws Exception {
        try {
            JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(id)
                    .orElseThrow(() -> new Exception("Job Department not found"));

            jobDepartmentEntity.setJobDept(jobDepartmentDto.getJobDept());
            jobDepartmentEntity.setName(jobDepartmentDto.getName());
            jobDepartmentEntity.setDescription(jobDepartmentDto.getDescription());
            jobDepartmentEntity.setSalaryRange(jobDepartmentDto.getSalaryRange());

            return jobDepartmentRepository.save(jobDepartmentEntity);
        } catch (Exception e) {
            throw new Exception("Error updating job department: " + e.getMessage());
        }
    }

    // Update Salary
    public SalaryEntity updateSalary(Long id, SalaryDto salaryDto) throws Exception {
        try {
            SalaryEntity salaryEntity = salaryRepository.findById(id)
                    .orElseThrow(() -> new Exception("Salary not found"));

            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(salaryDto.getJobDepartmentDto().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            salaryEntity.setAmount(salaryDto.getAmount());
            salaryEntity.setAnnual(salaryDto.getAnnual());
            salaryEntity.setBonus(salaryDto.getBonus());
            salaryEntity.setJobDepartment(jobDepartment);

            return salaryRepository.save(salaryEntity);
        } catch (Exception e) {
            throw new Exception("Error updating salary: " + e.getMessage());
        }
    }

    // Update Qualification
    public QualificationEntity updateQualification(Long id, QualificationDto qualificationDto) throws Exception {
        try {
            QualificationEntity qualificationEntity = qualificationRepository.findById(id)
                    .orElseThrow(() -> new Exception("Qualification not found"));

            EmployeeEntity employee = employeeRepository.findById(qualificationDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            qualificationEntity.setPosition(qualificationDto.getPosition());
            qualificationEntity.setRequirements(qualificationDto.getRequirements());
            qualificationEntity.setDateIn(qualificationDto.getDateIn());
            qualificationEntity.setEmployeeEntity(employee);

            return qualificationRepository.save(qualificationEntity);
        } catch (Exception e) {
            throw new Exception("Error updating qualification: " + e.getMessage());
        }
    }

    // Update Leave
    public LeaveEntity updateLeave(Long id, LeaveDto leaveDto) throws Exception {
        try {
            LeaveEntity leaveEntity = leaveRepository.findById(id)
                    .orElseThrow(() -> new Exception("Leave not found"));

            EmployeeEntity employee = employeeRepository.findById(leaveDto.getEmployeeDto().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            leaveEntity.setDate(leaveDto.getDate());
            leaveEntity.setReason(leaveDto.getReason());
            leaveEntity.setEmployeeEntity(employee);

            return leaveRepository.save(leaveEntity);
        } catch (Exception e) {
            throw new Exception("Error updating leave: " + e.getMessage());
        }
    }

    // Update Payroll
    public PayrollEntity updatePayroll(Long id, PayrollDto payrollDto) throws Exception {
        try {
            PayrollEntity payrollEntity = payrollRepository.findById(id)
                    .orElseThrow(() -> new Exception("Payroll not found"));

            EmployeeEntity employee = employeeRepository.findById(payrollDto.getEmployeeDto().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(payrollDto.getJobDepartmentDto().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            SalaryEntity salary = salaryRepository.findById(payrollDto.getSalaryDto().getSalaryID())
                    .orElseThrow(() -> new Exception("Salary not found"));

            LeaveEntity leave = leaveRepository.findById(payrollDto.getLeaveDto().getLeaveId())
                    .orElseThrow(() -> new Exception("Leave not found"));

            payrollEntity.setDate(payrollDto.getDate());
            payrollEntity.setReport(payrollDto.getReport());
            payrollEntity.setTotalAmount(payrollDto.getTotalAmount());
            payrollEntity.setEmployeeEntity(employee);
            payrollEntity.setJobDepartment(jobDepartment);
            payrollEntity.setSalaryEntity(salary);
            payrollEntity.setLeave(leave);

            return payrollRepository.save(payrollEntity);
        } catch (Exception e) {
            throw new Exception("Error updating payroll: " + e.getMessage());
        }
    }


}

