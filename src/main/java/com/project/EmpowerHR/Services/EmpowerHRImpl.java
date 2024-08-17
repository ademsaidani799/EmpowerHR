package com.project.EmpowerHR.Services;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;
import com.project.EmpowerHR.Interfaces.EmpowerHR;
import com.project.EmpowerHR.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpowerHRImpl implements EmpowerHR {
    @Autowired
    private ModelMapper modelMapper;

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
    public EmployeeDto createEmployee(EmployeeDto employeeDto) throws Exception {
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
        EmployeeEntity savedEmployee =employeeRepository.saveAndFlush(employeeEntity);

        // Save the new employee
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public JobDepartmentDto createJobDepartment(JobDepartmentDto jobDepartmentDto) {
        // Create a new JobDepartmentEntity
        JobDepartmentEntity jobDepartmentEntity = new JobDepartmentEntity();
        jobDepartmentEntity.setJobDept(jobDepartmentDto.getJobDept());
        jobDepartmentEntity.setName(jobDepartmentDto.getName());
        jobDepartmentEntity.setDescription(jobDepartmentDto.getDescription());
        jobDepartmentEntity.setSalaryRange(jobDepartmentDto.getSalaryRange());

        JobDepartmentEntity savedJobDept =jobDepartmentRepository.saveAndFlush(jobDepartmentEntity);

        // Save the new employee
        return modelMapper.map(savedJobDept, JobDepartmentDto.class);

    }

    public SalaryDto createSalary(SalaryDto salaryDto) throws Exception {
        try {
            // Retrieve JobDepartmentEntity
            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(salaryDto.getJobDepartment().getJobID())
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

            SalaryEntity savedSalary =salaryRepository.saveAndFlush(salaryEntity);;

            // Save the new employee
            return modelMapper.map(savedSalary, SalaryDto.class);

        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating salary: " + e.getMessage());
        }
    }
    public QualificationDto createQualification(QualificationDto qualificationDto) throws Exception {
        try {
            // Retrieve the EmployeeEntity
            EmployeeEntity employee = employeeRepository.findById(qualificationDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            // Create and populate QualificationEntity
            QualificationEntity qualificationEntity = new QualificationEntity();
            qualificationEntity.setPosition(qualificationDto.getPosition());
            qualificationEntity.setRequirements(qualificationDto.getRequirements());
            qualificationEntity.setDateIn(qualificationDto.getDateIn());
            qualificationEntity.setEmployee(employee);

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

            QualificationEntity savedQualification =qualificationRepository.saveAndFlush(qualificationEntity);;

            // Save the new employee
            return modelMapper.map(savedQualification, QualificationDto.class);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating qualification: " + e.getMessage());
        }
    }
    public LeaveDto createLeave(LeaveDto leaveDto) throws Exception {
        try {
            // Retrieve the EmployeeEntity
            EmployeeEntity employee = employeeRepository.findById(leaveDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            // Create and populate LeaveEntity
            LeaveEntity leaveEntity = new LeaveEntity();
            leaveEntity.setDate(leaveDto.getDate());
            leaveEntity.setReason(leaveDto.getReason());
            leaveEntity.setEmployee(employee);


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

            LeaveEntity savedLeave =leaveRepository.saveAndFlush(leaveEntity);

            // Save the new employee
            return modelMapper.map(savedLeave, LeaveDto.class);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating leave: " + e.getMessage());
        }
    }
    public PayrollDto createPayroll(PayrollDto payrollDto) throws Exception {
        try {
            // Retrieve the EmployeeEntity
            EmployeeEntity employee = employeeRepository.findById(payrollDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            // Retrieve the JobDepartmentEntity
            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(payrollDto.getJobDepartment().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            // Retrieve the SalaryEntity
            SalaryEntity salary = salaryRepository.findById(payrollDto.getSalary().getSalaryID())
                    .orElseThrow(() -> new Exception("Salary not found"));

            // Retrieve the LeaveEntity
            LeaveEntity leave = leaveRepository.findById(payrollDto.getLeave().getLeaveId())
                    .orElseThrow(() -> new Exception("Leave not found"));

            // Create and populate PayrollEntity
            PayrollEntity payrollEntity = new PayrollEntity();
            payrollEntity.setDate(payrollDto.getDate());
            payrollEntity.setReport(payrollDto.getReport());
            payrollEntity.setTotalAmount(payrollDto.getTotalAmount());
            payrollEntity.setEmployee(employee);
            payrollEntity.setJobDepartment(jobDepartment);
            payrollEntity.setSalary(salary);
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

            PayrollEntity savedLeave =payrollRepository.saveAndFlush(payrollEntity);

            // Save the new employee
            return modelMapper.map(savedLeave, PayrollDto.class);
        } catch (Exception e) {
            // Handle the exception accordingly
            throw new Exception("Error creating payroll: " + e.getMessage());
        }
    }

    // Create User
    public UserDto createUser(UserDto userDto) throws Exception {
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

            UserEntity savedLeave =userRepository.saveAndFlush(userEntity);

            // Save the new employee
            return modelMapper.map(savedLeave, UserDto.class);
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
    public EmployeeDto getEmployeeById(Long id) throws Exception {
try {

    return modelMapper.map(employeeRepository.findById(id), EmployeeDto.class);
}catch (Exception e) {
   throw new Exception("Employee not found");
}
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    // JobDepartment read methods
    public JobDepartmentDto getJobDepartmentById(Long id) throws Exception {
        try {

            return modelMapper.map(jobDepartmentRepository.findById(id), JobDepartmentDto.class);
        }catch (Exception e) {
            throw new Exception("job dept not found");
        }
    }

    public List<JobDepartmentDto> getAllJobDepartments() {
        return jobDepartmentRepository.findAll().stream()
                .map(jobdept -> modelMapper.map(jobdept, JobDepartmentDto.class))
                .collect(Collectors.toList());
    }

    // Leave read methods
    public LeaveDto getLeaveById(Long id) throws Exception {
        try {

            return modelMapper.map(leaveRepository.findById(id), LeaveDto.class);
        }catch (Exception e) {
            throw new Exception("job dept not found");
        }
    }

    public List<LeaveDto> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(leave -> modelMapper.map(leave, LeaveDto.class))
                .collect(Collectors.toList());    }

    // Payroll read methods
    public PayrollDto getPayrollById(Long id) throws Exception {
        try {

            return modelMapper.map(payrollRepository.findById(id), PayrollDto.class);
        }catch (Exception e) {
            throw new Exception("job dept not found");
        }
    }

    public List<PayrollDto> getAllPayrolls() {
        return payrollRepository.findAll().stream()
                .map(leave -> modelMapper.map(leave, PayrollDto.class))
                .collect(Collectors.toList());
    }

    // Qualification read methods
    public QualificationDto getQualificationById(Long id) throws Exception {
        try {

            return modelMapper.map(qualificationRepository.findById(id), QualificationDto.class);
        }catch (Exception e) {
            throw new Exception("job dept not found");
        }
    }

    public List<QualificationDto> getAllQualifications() {

        return qualificationRepository.findAll().stream()
                .map(leave -> modelMapper.map(leave, QualificationDto.class))
                .collect(Collectors.toList());

    }

    // User read methods
    public UserDto getUserById(Long id) throws Exception {
        try {

            return modelMapper.map(userRepository.findById(id), UserDto.class);
        }catch (Exception e) {
            throw new Exception("job dept not found");
        }
    }

    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream()
                .map(leave -> modelMapper.map(leave, UserDto.class))
                .collect(Collectors.toList());


    }

    // Salary read methods
    public SalaryDto getSalaryById(Long id) throws Exception {
        try {

            return modelMapper.map(salaryRepository.findById(id), SalaryDto.class);
        }catch (Exception e) {
            throw new Exception("job dept not found");
        }
    }

    public List<SalaryDto> getAllSalaries(){
        return salaryRepository.findAll().stream()
                .map(leave -> modelMapper.map(leave, SalaryDto.class))
            .collect(Collectors.toList());
}

    //implementation of all update methods


    // Update User
    public UserDto updateUser(Long id, UserDto userDto) throws Exception {
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


            UserEntity savedLeave =userRepository.saveAndFlush(userEntity);

            // Save the new employee
            return modelMapper.map(savedLeave, UserDto.class);
        } catch (Exception e) {
            throw new Exception("Error updating user: " + e.getMessage());
        }
    }

    // Update Employee
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) throws Exception {
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

            EmployeeEntity savedEmployee =employeeRepository.saveAndFlush(employeeEntity);

            // Save the new employee
            return modelMapper.map(savedEmployee, EmployeeDto.class);
        } catch (Exception e) {
            throw new Exception("Error updating employee: " + e.getMessage());
        }
    }

    // Update Job Department
    public JobDepartmentDto updateJobDepartment(Long id, JobDepartmentDto jobDepartmentDto) throws Exception {
        try {
            JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(id)
                    .orElseThrow(() -> new Exception("Job Department not found"));

            jobDepartmentEntity.setJobDept(jobDepartmentDto.getJobDept());
            jobDepartmentEntity.setName(jobDepartmentDto.getName());
            jobDepartmentEntity.setDescription(jobDepartmentDto.getDescription());
            jobDepartmentEntity.setSalaryRange(jobDepartmentDto.getSalaryRange());


            JobDepartmentEntity saveJobDept =jobDepartmentRepository.saveAndFlush(jobDepartmentEntity);

            // Save the new employee
            return modelMapper.map(saveJobDept, JobDepartmentDto.class);

        } catch (Exception e) {
            throw new Exception("Error updating job department: " + e.getMessage());
        }
    }

    // Update Salary
    public SalaryDto updateSalary(Long id, SalaryDto salaryDto) throws Exception {
        try {
            SalaryEntity salaryEntity = salaryRepository.findById(id)
                    .orElseThrow(() -> new Exception("Salary not found"));

            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(salaryDto.getJobDepartment().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            salaryEntity.setAmount(salaryDto.getAmount());
            salaryEntity.setAnnual(salaryDto.getAnnual());
            salaryEntity.setBonus(salaryDto.getBonus());
            salaryEntity.setJobDepartment(jobDepartment);

            SalaryEntity savedSalary=salaryRepository.saveAndFlush(salaryEntity);

            // Save the new employee
            return modelMapper.map(savedSalary, SalaryDto.class);

        } catch (Exception e) {
            throw new Exception("Error updating salary: " + e.getMessage());
        }
    }

    // Update Qualification
    public QualificationDto updateQualification(Long id, QualificationDto qualificationDto) throws Exception {
        try {
            QualificationEntity qualificationEntity = qualificationRepository.findById(id)
                    .orElseThrow(() -> new Exception("Qualification not found"));

            EmployeeEntity employee = employeeRepository.findById(qualificationDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            qualificationEntity.setPosition(qualificationDto.getPosition());
            qualificationEntity.setRequirements(qualificationDto.getRequirements());
            qualificationEntity.setDateIn(qualificationDto.getDateIn());
            qualificationEntity.setEmployee(employee);

            QualificationEntity savedSalary=qualificationRepository.saveAndFlush(qualificationEntity);

            // Save the new employee
            return modelMapper.map(savedSalary, QualificationDto.class);

        } catch (Exception e) {
            throw new Exception("Error updating qualification: " + e.getMessage());
        }
    }

    // Update Leave
    public LeaveDto updateLeave(Long id, LeaveDto leaveDto) throws Exception {
        try {
            LeaveEntity leaveEntity = leaveRepository.findById(id)
                    .orElseThrow(() -> new Exception("Leave not found"));

            EmployeeEntity employee = employeeRepository.findById(leaveDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            leaveEntity.setDate(leaveDto.getDate());
            leaveEntity.setReason(leaveDto.getReason());
            leaveEntity.setEmployee(employee);


            LeaveEntity savedLeave =leaveRepository.saveAndFlush(leaveEntity);

            // Save the new employee
            return modelMapper.map(savedLeave, LeaveDto.class);
        } catch (Exception e) {
            throw new Exception("Error updating leave: " + e.getMessage());
        }
    }

    // Update Payroll
    public PayrollDto updatePayroll(Long id, PayrollDto payrollDto) throws Exception {
        try {
            PayrollEntity payrollEntity = payrollRepository.findById(id)
                    .orElseThrow(() -> new Exception("Payroll not found"));

            EmployeeEntity employee = employeeRepository.findById(payrollDto.getEmployee().getEmpID())
                    .orElseThrow(() -> new Exception("Employee not found"));

            JobDepartmentEntity jobDepartment = jobDepartmentRepository.findById(payrollDto.getJobDepartment().getJobID())
                    .orElseThrow(() -> new Exception("Job Department not found"));

            SalaryEntity salary = salaryRepository.findById(payrollDto.getSalary().getSalaryID())
                    .orElseThrow(() -> new Exception("Salary not found"));

            LeaveEntity leave = leaveRepository.findById(payrollDto.getLeave().getLeaveId())
                    .orElseThrow(() -> new Exception("Leave not found"));

            payrollEntity.setDate(payrollDto.getDate());
            payrollEntity.setReport(payrollDto.getReport());
            payrollEntity.setTotalAmount(payrollDto.getTotalAmount());
            payrollEntity.setEmployee(employee);
            payrollEntity.setJobDepartment(jobDepartment);
            payrollEntity.setSalary(salary);
            payrollEntity.setLeave(leave);


            PayrollEntity savedLeave =payrollRepository.saveAndFlush(payrollEntity);

            // Save the new employee
            return modelMapper.map(savedLeave, PayrollDto.class);
        } catch (Exception e) {
            throw new Exception("Error updating payroll: " + e.getMessage());
        }
    }


}

