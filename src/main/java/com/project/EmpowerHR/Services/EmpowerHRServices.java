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

        // Handle QualificationEntities
        if (employee.getQualificationList() != null) {
            for (Qualification qualification : employee.getQualificationList()) {
                QualificationEntity qualificationEntity = new QualificationEntity();
                qualificationEntity.setPosition(qualification.getPosition());
                qualificationEntity.setRequirements(qualification.getRequirements());
                qualificationEntity.setDateIn(qualification.getDateIn());
                qualificationEntity.setEmployee(employeeEntity);
                qualificationRepository.save(qualificationEntity);
                employeeEntity.getQualificationList().add(qualificationEntity);
            }
        }

        // Handle PayrollEntities
        if (employee.getPayrollEntities() != null) {
            for (Payroll payroll : employee.getPayrollEntities()) {
                PayrollEntity payrollEntity = new PayrollEntity();
                payrollEntity.setJobID(payroll.getJobID());
                payrollEntity.setSalaryID(payroll.getSalaryID());
                payrollEntity.setLeaveID(payroll.getLeaveID());
                payrollEntity.setDate(payroll.getDate());
                payrollEntity.setReport(payroll.getReport());
                payrollEntity.setTotalAmount(payroll.getTotalAmount());
                payrollEntity.setEmployee(employeeEntity);
                payrollRepository.save(payrollEntity);
                employeeEntity.getPayrolls().add(payrollEntity);
            }
        }



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

            // Clear existing qualifications and add new ones
            employeeEntity.getQualificationList().clear();
            if (employee.getQualificationList() != null) {
                for (Qualification qualification : employee.getQualificationList()) {
                    QualificationEntity qualificationEntity = new QualificationEntity();
                    qualificationEntity.setPosition(qualification.getPosition());
                    qualificationEntity.setRequirements(qualification.getRequirements());
                    qualificationEntity.setDateIn(qualification.getDateIn());
                    qualificationEntity.setEmployee(employeeEntity);
                    qualificationRepository.save(qualificationEntity);
                    employeeEntity.getQualificationList().add(qualificationEntity);
                }
            }

            // Clear existing payrolls and add new ones
            employeeEntity.getPayrolls().clear();
            if (employee.getPayrollEntities() != null) {
                for (Payroll payroll : employee.getPayrollEntities()) {
                    PayrollEntity payrollEntity = new PayrollEntity();
                    payrollEntity.setJobID(payroll.getJobID());
                    payrollEntity.setSalaryID(payroll.getSalaryID());
                    payrollEntity.setLeaveID(payroll.getLeaveID());
                    payrollEntity.setDate(payroll.getDate());
                    payrollEntity.setReport(payroll.getReport());
                    payrollEntity.setTotalAmount(payroll.getTotalAmount());
                    payrollEntity.setEmployee(employeeEntity);
                    payrollRepository.save(payrollEntity);
                    employeeEntity.getPayrolls().add(payrollEntity);
                }
            }

            // Clear existing leaves and add new ones
            employeeEntity.getLeaves().clear();
            if (employee.getLeaves() != null) {
                for (Leave leave : employee.getLeaves()) {
                    LeaveEntity leaveEntity = new LeaveEntity();
                    leaveEntity.setDate(leave.getDate());
                    leaveEntity.setReason(leave.getReason());
                    leaveEntity.setEmployee(employeeEntity);
                    leaveRepository.save(leaveEntity);
                    employeeEntity.getLeaves().add(leaveEntity);
                }
            }

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
    public JobDepartmentEntity createJobDepartment(JobDepartment jobDepartment) {
        JobDepartmentEntity jobDepartmentEntity = new JobDepartmentEntity();
        jobDepartmentEntity.setJobDept(jobDepartment.getJobDept());
        jobDepartmentEntity.setName(jobDepartment.getName());
        jobDepartmentEntity.setDescription(jobDepartment.getDescription());
        jobDepartmentEntity.setSalaryRange(jobDepartment.getSalaryRange());

        // Initialize the lists if they are null
        if (jobDepartmentEntity.getSalaryEntityList() == null) {
            jobDepartmentEntity.setSalaryEntityList(new ArrayList<>());
        }
        if (jobDepartmentEntity.getPayrollEntityList() == null) {
            jobDepartmentEntity.setPayrollEntityList(new ArrayList<>());
        }

        // Handle SalaryEntities
        if (jobDepartment.getSalaryList() != null) {
            for (Salary salary : jobDepartment.getSalaryList()) {
                SalaryEntity salaryEntity = new SalaryEntity();
                salaryEntity.setJobID(salary.getJobID());
                salaryEntity.setAmount(salary.getAmount());
                salaryEntity.setAnnual(salary.getAnnual());
                salaryEntity.setBonus(salary.getBonus());
                salaryEntity.setJobDepartment(jobDepartmentEntity);
                salaryRepository.save(salaryEntity);
                jobDepartmentEntity.getSalaryEntityList().add(salaryEntity);
            }
        }

        // Handle PayrollEntities
        if (jobDepartment.getPayrollList() != null) {
            for (Payroll payroll : jobDepartment.getPayrollList()) {
                PayrollEntity payrollEntity = new PayrollEntity();
                payrollEntity.setEmpID(payroll.getEmpID());
                payrollEntity.setJobID(payroll.getJobID());
                payrollEntity.setSalaryID(payroll.getSalaryID());
                payrollEntity.setLeaveID(payroll.getLeaveID());
                payrollEntity.setDate(payroll.getDate());
                payrollEntity.setReport(payroll.getReport());
                payrollEntity.setTotalAmount(payroll.getTotalAmount());
                payrollEntity.setJobDepartment(jobDepartmentEntity);
                payrollRepository.save(payrollEntity);
                jobDepartmentEntity.getPayrollEntityList().add(payrollEntity);
            }
        }

        return jobDepartmentRepository.save(jobDepartmentEntity);
    }

    public JobDepartmentEntity getJobDepartmentById(Long id) {
        Optional<JobDepartmentEntity> optionalJobDepartmentEntity = jobDepartmentRepository.findById(Math.toIntExact(id));
        return optionalJobDepartmentEntity.orElse(null);
    }

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

            // Handle SalaryEntities
            jobDepartmentEntity.getSalaryEntityList().clear();
            if (jobDepartment.getSalaryList() != null) {
                for (Salary salary : jobDepartment.getSalaryList()) {
                    SalaryEntity salaryEntity = new SalaryEntity();
                    salaryEntity.setJobID(salary.getJobID());
                    salaryEntity.setAmount(salary.getAmount());
                    salaryEntity.setAnnual(salary.getAnnual());
                    salaryEntity.setBonus(salary.getBonus());
                    salaryEntity.setJobDepartment(jobDepartmentEntity);
                    salaryRepository.save(salaryEntity);
                    jobDepartmentEntity.getSalaryEntityList().add(salaryEntity);
                }
            }

            // Handle PayrollEntities
            jobDepartmentEntity.getPayrollEntityList().clear();
            if (jobDepartment.getPayrollList() != null) {
                for (Payroll payroll : jobDepartment.getPayrollList()) {
                    PayrollEntity payrollEntity = new PayrollEntity();
                    payrollEntity.setEmpID(payroll.getEmpID());
                    payrollEntity.setJobID(payroll.getJobID());
                    payrollEntity.setSalaryID(payroll.getSalaryID());
                    payrollEntity.setLeaveID(payroll.getLeaveID());
                    payrollEntity.setDate(payroll.getDate());
                    payrollEntity.setReport(payroll.getReport());
                    payrollEntity.setTotalAmount(payroll.getTotalAmount());
                    payrollEntity.setJobDepartment(jobDepartmentEntity);
                    payrollRepository.save(payrollEntity);
                    jobDepartmentEntity.getPayrollEntityList().add(payrollEntity);
                }
            }

            return jobDepartmentRepository.save(jobDepartmentEntity);
        } else {
            return null;
        }
    }

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

        leaveEntity.setPayrollEntityList(new ArrayList<>());
        if (leave.getPayrollList() != null) {
            for (Payroll payroll : leave.getPayrollList()) {
                PayrollEntity payrollEntity = new PayrollEntity();
                payrollEntity.setJobID(payroll.getJobID());
                payrollEntity.setSalaryID(payroll.getSalaryID());
                payrollEntity.setLeaveID(payroll.getLeaveID());
                payrollEntity.setDate(payroll.getDate());
                payrollEntity.setReport(payroll.getReport());
                payrollEntity.setTotalAmount(payroll.getTotalAmount());
                payrollEntity.setLeave(leaveEntity);
                payrollRepository.save(payrollEntity);
                leaveEntity.getPayrollEntityList().add(payrollEntity);
            }
        }
        employeeEntity.getLeaves().add(leaveEntity);


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

        // Update Payrolls
        leaveEntity.getPayrollEntityList().clear();
        if (leave.getPayrollList() != null) {
            for (Payroll payroll : leave.getPayrollList()) {
                PayrollEntity payrollEntity = new PayrollEntity();
                payrollEntity.setJobID(payroll.getJobID());
                payrollEntity.setSalaryID(payroll.getSalaryID());
                payrollEntity.setLeaveID(payroll.getLeaveID());
                payrollEntity.setDate(payroll.getDate());
                payrollEntity.setReport(payroll.getReport());
                payrollEntity.setTotalAmount(payroll.getTotalAmount());
                payrollEntity.setLeave(leaveEntity);
                payrollRepository.save(payrollEntity);
                leaveEntity.getPayrollEntityList().add(payrollEntity);
            }
        }

        return leaveRepository.save(leaveEntity);
    }

    @Override
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(Math.toIntExact(id));
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

        payrollEntity.setEmpID(payroll.getEmpID());
        payrollEntity.setJobID(payroll.getJobID());
        payrollEntity.setSalaryID(payroll.getSalaryID());
        payrollEntity.setLeaveID(payroll.getLeaveID());
        payrollEntity.setDate(payroll.getDate());
        payrollEntity.setReport(payroll.getReport());
        payrollEntity.setTotalAmount(payroll.getTotalAmount());

        // Update relationships
        if (payroll.getJobID() != 0) {
            payrollEntity.setJobDepartment(jobDepartmentRepository.findById(payroll.getJobID()).orElse(null));
        }
        if (payroll.getEmpID() != 0) {
            payrollEntity.setEmployee(employeeRepository.findById(payroll.getEmpID()).orElse(null));
        }
        if (payroll.getSalaryID() != 0) {
            payrollEntity.setSalary(salaryRepository.findById(payroll.getSalaryID()).orElse(null));
        }
        if (payroll.getLeaveID() != 0) {
            payrollEntity.setLeave(leaveRepository.findById(payroll.getLeaveID()).orElse(null));
        }

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
        salaryEntity.setJobID(salary.getJobID());
        salaryEntity.setAmount(salary.getAmount());
        salaryEntity.setAnnual(salary.getAnnual());
        salaryEntity.setBonus(salary.getBonus());

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(salary.getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        salaryEntity.setJobDepartment(jobDepartmentEntity);

        if (salary.getPayrollEntityList() != null) {
            for (Payroll payroll : salary.getPayrollEntityList()) {
                PayrollEntity payrollEntity = new PayrollEntity();
                payrollEntity.setEmpID(payroll.getEmpID());
                payrollEntity.setJobID(payroll.getJobID());
                payrollEntity.setSalaryID(payroll.getSalaryID());
                payrollEntity.setLeaveID(payroll.getLeaveID());
                payrollEntity.setDate(payroll.getDate());
                payrollEntity.setReport(payroll.getReport());
                payrollEntity.setTotalAmount(payroll.getTotalAmount());
                payrollEntity.setSalary(salaryEntity);
                payrollRepository.save(payrollEntity);
                salaryEntity.getPayrollList().add(payrollEntity);
            }
        }

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
        salaryEntity.setJobID(salary.getJobID());
        salaryEntity.setAmount(salary.getAmount());
        salaryEntity.setAnnual(salary.getAnnual());
        salaryEntity.setBonus(salary.getBonus());

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(salary.getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        salaryEntity.setJobDepartment(jobDepartmentEntity);

        // Update Payrolls
        salaryEntity.getPayrollList().clear();
        if (salary.getPayrollEntityList() != null) {
            for (Payroll payroll : salary.getPayrollEntityList()) {
                PayrollEntity payrollEntity = new PayrollEntity();
                payrollEntity.setEmpID(payroll.getEmpID());
                payrollEntity.setJobID(payroll.getJobID());
                payrollEntity.setSalaryID(payroll.getSalaryID());
                payrollEntity.setLeaveID(payroll.getLeaveID());
                payrollEntity.setDate(payroll.getDate());
                payrollEntity.setReport(payroll.getReport());
                payrollEntity.setTotalAmount(payroll.getTotalAmount());
                payrollEntity.setSalary(salaryEntity);
                payrollRepository.save(payrollEntity);
                salaryEntity.getPayrollList().add(payrollEntity);
            }
        }

        return salaryRepository.save(salaryEntity);
    }

    @Override
    public void deleteSalary(Long id) {
        salaryRepository.deleteById(Math.toIntExact(id));
    }
}