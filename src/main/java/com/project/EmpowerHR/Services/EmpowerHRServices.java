package com.project.EmpowerHR.Services;


import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;
import com.project.EmpowerHR.Repositories.*;
import com.project.EmpowerHR.Interfaces.EmpowerHR;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpowerHRServices implements EmpowerHR {



    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobDepartementRepository jobDepartmentRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalaryRepository salaryRepository;


    // Employee CRUD methods
    @Override
    @Transactional
    public EmployeeEntity createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setGender(employee.getGender());
        employeeEntity.setAge(employee.getAge());
        employeeEntity.setContactAddress(employee.getContactAddress());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setPassword(employee.getPassword());



        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(Math.toIntExact(id));
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EntityNotFoundException("Employee not found with id " + id);
        }
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public EmployeeEntity updateEmployee(Employee employee) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employee.getEmpID());
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployee.get();
            employeeEntity.setFirstName(employee.getFirstName());
            employeeEntity.setLastName(employee.getLastName());
            employeeEntity.setGender(employee.getGender());
            employeeEntity.setAge(employee.getAge());
            employeeEntity.setContactAddress(employee.getContactAddress());
            employeeEntity.setEmail(employee.getEmail());
            employeeEntity.setPassword(employee.getPassword());


            return employeeRepository.save(employeeEntity);
        } else {
            throw new EntityNotFoundException("Employee not found with id " + employee.getEmpID());
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(Math.toIntExact(id));
    }
    @Transactional
    @Override
    public JobDepartmentEntity createJobDepartment(JobDepartment jobDepartment) {
        JobDepartmentEntity jobDepartmentEntity = new JobDepartmentEntity();
        jobDepartmentEntity.setJobDept(jobDepartment.getJobDept());
        jobDepartmentEntity.setName(jobDepartment.getName());
        jobDepartmentEntity.setDescription(jobDepartment.getDescription());
        jobDepartmentEntity.setSalaryRange(jobDepartment.getSalaryRange());


        return jobDepartmentRepository.save(jobDepartmentEntity);
    }
@Override
    public JobDepartmentEntity getJobDepartmentById(Long id) {
        Optional<JobDepartmentEntity> optionalJobDepartmentEntity = jobDepartmentRepository.findById(Math.toIntExact(id));
        return optionalJobDepartmentEntity.orElse(null);
    }
@Override
    public List<JobDepartmentEntity> getAllJobDepartments() {
        return jobDepartmentRepository.findAll();
    }

    @Transactional
    public JobDepartmentEntity updateJobDepartment(JobDepartment jobDepartment) {
        Optional<JobDepartmentEntity> optionalJobDepartmentEntity = jobDepartmentRepository.findById(jobDepartment.getJobID());
        if (optionalJobDepartmentEntity.isPresent()) {
            JobDepartmentEntity jobDepartmentEntity = optionalJobDepartmentEntity.get();
            jobDepartmentEntity.setJobDept(jobDepartment.getJobDept());
            jobDepartmentEntity.setName(jobDepartment.getName());
            jobDepartmentEntity.setDescription(jobDepartment.getDescription());
            jobDepartmentEntity.setSalaryRange(jobDepartment.getSalaryRange());

            return jobDepartmentRepository.save(jobDepartmentEntity);
        } else {
            return null;
        }
    }
@Override
    @Transactional
    public void deleteJobDepartment(Long id) {
        jobDepartmentRepository.deleteById(Math.toIntExact(id));
    }
    @Override
    @Transactional
    public LeaveEntity createLeave(Leave leave) {
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setDate(leave.getDate());
        leaveEntity.setReason(leave.getReason());

        EmployeeEntity employeeEntity = employeeRepository.findById(leave.getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        leaveEntity.setEmployee(employeeEntity);

      employeeEntity.getLeaves().add(leaveEntity);
      employeeRepository.save(employeeEntity);
        return leaveRepository.save(leaveEntity);
    }

    @Override
    public LeaveEntity getLeaveById(Long id) {
        return leaveRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("Leave not found"));
    }

    @Override
    public List<LeaveEntity> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    @Transactional
    public LeaveEntity updateLeave(Leave leave) {
        LeaveEntity leaveEntity = leaveRepository.findById(leave.getLeaveId())
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leaveEntity.setDate(leave.getDate());
        leaveEntity.setReason(leave.getReason());

        EmployeeEntity employeeEntity = employeeRepository.findById(leave.getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        leaveEntity.setEmployee(employeeEntity);

        employeeEntity.getLeaves().add(leaveEntity);
        employeeRepository.save(employeeEntity);
        return leaveRepository.save(leaveEntity);

    }

    @Override
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(Math.toIntExact(id));
    }

    public PayrollEntity createPayrollEntity(Payroll payroll){
            PayrollEntity payrollEntity = new PayrollEntity();
        payrollEntity.setDate(payroll.getDate());
        payrollEntity.setReport(payroll.getReport());
        payrollEntity.setTotalAmount(payroll.getTotalAmount());
        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(payroll.getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setJobDepartment(jobDepartmentEntity);

        jobDepartmentEntity.getPayrollEntityList().add(payrollEntity);
        jobDepartmentRepository.save(jobDepartmentEntity);

        SalaryEntity salaryEntity = salaryRepository.findById(payroll.getSalaryID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setSalaryEntity(salaryEntity);

        salaryEntity.getPayrollList().add(payrollEntity);
        salaryRepository.save(salaryEntity);


        EmployeeEntity employeeEntity = employeeRepository.findById(payroll.getEmpID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setEmployeeEntity(employeeEntity);

        employeeEntity.getPayrolls().add(payrollEntity);
        employeeRepository.save(employeeEntity);

        LeaveEntity leaveEntity = leaveRepository.findById(payroll.getLeaveID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setLeave(leaveEntity);

        leaveEntity.getPayrollEntityList().add(payrollEntity);
        leaveRepository.save(leaveEntity);



        return payrollRepository.save(payrollEntity);
    }
    @Override
    public PayrollEntity getPayrollById(Long id) {
        return payrollRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("Payroll not found"));
    }

    @Override
    public List<PayrollEntity> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    @Override
    public PayrollEntity updatePayroll(Payroll payroll) {
        PayrollEntity payrollEntity = payrollRepository.findById((int) payroll.getPayrollID())
                .orElseThrow(() -> new RuntimeException("Payroll not found"));


        payrollEntity.setDate(payroll.getDate());
        payrollEntity.setReport(payroll.getReport());
        payrollEntity.setTotalAmount(payroll.getTotalAmount());

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(payroll.getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setJobDepartment(jobDepartmentEntity);

        jobDepartmentEntity.getPayrollEntityList().add(payrollEntity);
        jobDepartmentRepository.save(jobDepartmentEntity);

        SalaryEntity salaryEntity = salaryRepository.findById(payroll.getSalaryID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setSalaryEntity(salaryEntity);

        salaryEntity.getPayrollList().add(payrollEntity);
        salaryRepository.save(salaryEntity);


        EmployeeEntity employeeEntity = employeeRepository.findById(payroll.getEmpID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setEmployeeEntity(employeeEntity);

        employeeEntity.getPayrolls().add(payrollEntity);
        employeeRepository.save(employeeEntity);

        LeaveEntity leaveEntity = leaveRepository.findById(payroll.getLeaveID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setLeave(leaveEntity);

        leaveEntity.getPayrollEntityList().add(payrollEntity);
        leaveRepository.save(leaveEntity);



        return payrollRepository.save(payrollEntity);
    }

    @Override
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public QualificationEntity getQualificationById(Long id) {
        return qualificationRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("qualification not found"));
    }



    public QualificationEntity createQualification(Qualification qualification){
        QualificationEntity qualificationEntity = new QualificationEntity();
        qualificationEntity.setPosition(qualification.getPosition());
        qualificationEntity.setRequirements(qualification.getRequirements());
        qualificationEntity.setDateIn(qualification.getDateIn());

        EmployeeEntity employeeEntity = employeeRepository.findById((int) qualification.getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        qualificationEntity.setEmployee(employeeEntity);
 employeeEntity.getQualificationList().add(qualificationEntity);
 employeeRepository.save(employeeEntity);
        return qualificationRepository.save(qualificationEntity);
    }
    @Override
    public List<QualificationEntity> getAllQualifications() {
        return qualificationRepository.findAll();
    }

    @Override
    public QualificationEntity updateQualification(Qualification qualification) {
        QualificationEntity qualificationEntity = qualificationRepository.findById((int) qualification.getQualID())
                .orElseThrow(() -> new RuntimeException("Qualification not found"));
        qualificationEntity.setEmpID(qualification.getEmpID());
        qualificationEntity.setPosition(qualification.getPosition());
        qualificationEntity.setRequirements(qualification.getRequirements());
        qualificationEntity.setDateIn(qualification.getDateIn());

        EmployeeEntity employeeEntity = employeeRepository.findById((int) qualification.getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        qualificationEntity.setEmployee(employeeEntity);
        employeeEntity.getQualificationList().add(qualificationEntity);
        employeeRepository.save(employeeEntity);
        return qualificationRepository.save(qualificationEntity);
    }

    @Override
    public void deleteQualification(Long id) {
        qualificationRepository.deleteById(Math.toIntExact(id));
    }
    @Override
    public UserEntity createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFname(user.getFname());
        userEntity.setLname(user.getLname());
        userEntity.setGender(user.getGender());
        userEntity.setAge(user.getAge());
        userEntity.setContactAdd(user.getContactAdd());
        userEntity.setAdminEmail(user.getAdminEmail());
        userEntity.setAdminPass(user.getAdminPass());

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(User user) {
        UserEntity userEntity = userRepository.findById((int) user.getAdminID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setFname(user.getFname());
        userEntity.setLname(user.getLname());
        userEntity.setGender(user.getGender());
        userEntity.setAge(user.getAge());
        userEntity.setContactAdd(user.getContactAdd());
        userEntity.setAdminEmail(user.getAdminEmail());
        userEntity.setAdminPass(user.getAdminPass());

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }
    @Override
    @Transactional
    public SalaryEntity createSalary(Salary salary) {
        SalaryEntity salaryEntity = new SalaryEntity();
        salaryEntity.setAmount(salary.getAmount());
        salaryEntity.setAnnual(salary.getAnnual());
        salaryEntity.setBonus(salary.getBonus());

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(salary.getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        salaryEntity.setJobDepartment(jobDepartmentEntity);

  jobDepartmentEntity.getSalaryEntityList().add(salaryEntity);
  jobDepartmentRepository.save(jobDepartmentEntity);

        return salaryRepository.save(salaryEntity);
    }

    @Override
    public SalaryEntity getSalaryById(Long id) {
        return salaryRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("Salary not found"));
    }

    @Override
    public List<SalaryEntity> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    @Transactional
    public SalaryEntity updateSalary(Salary salary) {
        SalaryEntity salaryEntity = salaryRepository.findById((int) salary.getSalaryID())
                .orElseThrow(() -> new RuntimeException("Salary not found"));
        salaryEntity.setAmount(salary.getAmount());
        salaryEntity.setAnnual(salary.getAnnual());
        salaryEntity.setBonus(salary.getBonus());

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(salary.getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        salaryEntity.setJobDepartment(jobDepartmentEntity);

        jobDepartmentEntity.getSalaryEntityList().add(salaryEntity);
        jobDepartmentRepository.save(jobDepartmentEntity);

        return salaryRepository.save(salaryEntity);
    }

    @Override
    public void deleteSalary(Long id) {
        salaryRepository.deleteById(Math.toIntExact(id));
    }
}