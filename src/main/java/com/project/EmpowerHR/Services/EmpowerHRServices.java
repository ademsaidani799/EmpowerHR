package com.project.EmpowerHR.Services;


import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;
import com.project.EmpowerHR.Repositories.*;
import com.project.EmpowerHR.Interfaces.EmpowerHR;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpowerHRServices implements EmpowerHR {


    @Autowired
     private ModelMapper modelMapper;

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

return  employeeRepository.save(convertEmployeeDtoToEmployeeEntity(employee));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(Math.toIntExact(id));
        if (optionalEmployee.isPresent()) {
            return convertEmployeeEntityToEmployee(optionalEmployee.get());
        } else {
            throw new EntityNotFoundException("Employee not found with id " + id);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee>  employees = new ArrayList<Employee>();
        for (EmployeeEntity employee : employeeRepository.findAll()) {
            employees.add(convertEmployeeEntityToEmployee(employee));
        }
        return employees;

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
        LeaveEntity leaveEntity = convertLeaveDtoToLeaveEntity(leave);

        EmployeeEntity employeeEntity = employeeRepository.findById(leave.getEmployee().getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        leaveEntity.setEmployeeEntity(employeeEntity);

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

        EmployeeEntity employeeEntity = employeeRepository.findById(leave.getEmployee().getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        leaveEntity.setEmployeeEntity(employeeEntity);

        employeeEntity.getLeaves().add(leaveEntity);
        employeeRepository.save(employeeEntity);
        return leaveRepository.save(leaveEntity);

    }

    @Override
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(Math.toIntExact(id));
    }

    public PayrollEntity createPayrollEntity(Payroll payroll){
            PayrollEntity payrollEntity =convertPayrollDtoToPayroollEntity(payroll);
        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(payroll.getJobDepartment().getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setJobDepartment(jobDepartmentEntity);

        jobDepartmentEntity.getPayrollEntityList().add(payrollEntity);
        jobDepartmentRepository.save(jobDepartmentEntity);

        SalaryEntity salaryEntity = salaryRepository.findById(payroll.getSalaryEntity().getSalaryID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setSalaryEntity(salaryEntity);

        salaryEntity.getPayrollEntityList().add(payrollEntity);
        salaryRepository.save(salaryEntity);


        EmployeeEntity employeeEntity = employeeRepository.findById(payroll.getEmployeeEntity().getEmpID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setEmployeeEntity(employeeEntity);

        employeeEntity.getPayrollEntities().add(payrollEntity);
        employeeRepository.save(employeeEntity);

        LeaveEntity leaveEntity = leaveRepository.findById(payroll.getLeave().getLeaveId())
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

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(payroll.getJobDepartment().getJobID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setJobDepartment(jobDepartmentEntity);

        jobDepartmentEntity.getPayrollEntityList().add(payrollEntity);
        jobDepartmentRepository.save(jobDepartmentEntity);

        SalaryEntity salaryEntity = salaryRepository.findById(payroll.getSalaryEntity().getSalaryID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setSalaryEntity(salaryEntity);

        salaryEntity.getPayrollEntityList().add(payrollEntity);
        salaryRepository.save(salaryEntity);


        EmployeeEntity employeeEntity = employeeRepository.findById(payroll.getEmployeeEntity().getEmpID())
                .orElseThrow(() -> new RuntimeException("Job Department not found"));
        payrollEntity.setEmployeeEntity(employeeEntity);

        employeeEntity.getPayrollEntities().add(payrollEntity);
        employeeRepository.save(employeeEntity);

        LeaveEntity leaveEntity = leaveRepository.findById(payroll.getLeave().getLeaveId())
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

        EmployeeEntity employeeEntity = employeeRepository.findById((int) qualification.getEmployee().getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        qualificationEntity.setEmployeeEntity(employeeEntity);
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
        qualificationEntity.setPosition(qualification.getPosition());
        qualificationEntity.setRequirements(qualification.getRequirements());
        qualificationEntity.setDateIn(qualification.getDateIn());

        EmployeeEntity employeeEntity = employeeRepository.findById((int) qualification.getEmployee().getEmpID())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        qualificationEntity.setEmployeeEntity(employeeEntity);
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
        return userRepository.save(convertUserDtoToUserEntity(user));
    }

    @Override
    public User getUserById(Long id) {
        return convertUserEntityToUser(userRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(convertUserEntityToUser(userEntity));
        }
        return users;
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = userRepository.findById((int) user.getAdminID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setFname(user.getFname());
        userEntity.setLname(user.getLname());
        userEntity.setGender(user.getGender());
        userEntity.setAge(user.getAge());
        userEntity.setContactAdd(user.getContactAdd());
        userEntity.setAdminEmail(user.getAdminEmail());
        userEntity.setAdminPass(user.getAdminPass());

        return convertUserEntityToUser(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }
    @Override
    @Transactional
    public SalaryEntity createSalary(Salary salary) {
        SalaryEntity salaryEntity = convertSalaryDtoToSalaryEntity(salary);

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(salary.getJobDepartment().getJobID())
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

        JobDepartmentEntity jobDepartmentEntity = jobDepartmentRepository.findById(salary.getJobDepartment().getJobID())
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
    public EmployeeEntity convertEmployeeDtoToEmployeeEntity(Employee employee) {
        TypeMap<Employee, EmployeeEntity> propertyMapper = this.modelMapper.createTypeMap(Employee.class,EmployeeEntity.class);
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setEmpID));
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setQualificationList));
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setPayrollEntities));
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setLeaves));
        return modelMapper.map(employee, EmployeeEntity.class);
    }
    public EmployeeEntity convertEmployeeDtoToEmployeeEntityOnNonNull(Employee employee) {
        TypeMap<Employee, EmployeeEntity> propertyMapper = this.modelMapper.createTypeMap(Employee.class, EmployeeEntity.class);
        propertyMapper.setProvider(p -> this.employeeRepository.findById(employee.getEmpID()).get());
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getEmpID, EmployeeEntity::setEmpID));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getAge, EmployeeEntity::setAge));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getEmail, EmployeeEntity::setEmail));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getGender, EmployeeEntity::setGender));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getContactAddress, EmployeeEntity::setContactAddress));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getFirstName, EmployeeEntity::setFirstName));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getLastName, EmployeeEntity::setLastName));
        propertyMapper.addMappings(mapper -> mapper.when(Conditions.isNull()).skip(Employee::getPassword, EmployeeEntity::setPassword));
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setQualificationList));
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setPayrollEntities));
        propertyMapper.addMappings(mapper -> mapper.skip(EmployeeEntity::setLeaves));
return modelMapper.map(employee, EmployeeEntity.class);
    }

    public LeaveEntity convertLeaveDtoToLeaveEntity(Leave leave) {
        TypeMap<Leave, LeaveEntity> propertyMapper = this.modelMapper.createTypeMap(Leave.class,LeaveEntity.class);
        propertyMapper.addMappings(mapper -> mapper.skip(LeaveEntity::setLeaveId));
        propertyMapper.addMappings(mapper -> mapper.skip(LeaveEntity::setPayrollEntityList));
        propertyMapper.addMappings(mapper -> mapper.skip(LeaveEntity::setEmployeeEntity));

        return modelMapper.map(leave, LeaveEntity.class);
    }
    public PayrollEntity convertPayrollDtoToPayroollEntity(Payroll payroll) {
        TypeMap<Payroll, PayrollEntity> propertyMapper = this.modelMapper.createTypeMap(Payroll.class,PayrollEntity.class);
        propertyMapper.addMappings(mapper -> mapper.skip(PayrollEntity::setPayrollID));
        propertyMapper.addMappings(mapper -> mapper.skip(PayrollEntity::setSalaryEntity));
        propertyMapper.addMappings(mapper -> mapper.skip(PayrollEntity::setEmployeeEntity));
        propertyMapper.addMappings(mapper -> mapper.skip(PayrollEntity::setJobDepartment));
        propertyMapper.addMappings(mapper -> mapper.skip(PayrollEntity::setLeave));

        return modelMapper.map(payroll, PayrollEntity.class);
    } public SalaryEntity convertSalaryDtoToSalaryEntity(Salary salary) {
        TypeMap<Salary, SalaryEntity> propertyMapper = this.modelMapper.createTypeMap(Salary.class,SalaryEntity.class);
        propertyMapper.addMappings(mapper -> mapper.skip(SalaryEntity::setPayrollEntityList));
        propertyMapper.addMappings(mapper -> mapper.skip(SalaryEntity::setJobDepartment));
        propertyMapper.addMappings(mapper -> mapper.skip(SalaryEntity::setSalaryID));

        return modelMapper.map(salary, SalaryEntity.class);
    } public UserEntity convertUserDtoToUserEntity(User user) {
        TypeMap<User, UserEntity> propertyMapper = this.modelMapper.createTypeMap(User.class,UserEntity.class);
        propertyMapper.addMappings(mapper -> mapper.skip(UserEntity::setAdminID));


        return modelMapper.map(user, UserEntity.class);
    }
    public Employee convertEmployeeEntityToEmployee(EmployeeEntity employeeEntity) {
        TypeMap<EmployeeEntity, Employee> propertyMapper = this.modelMapper.createTypeMap(EmployeeEntity.class, Employee.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Employee::setPassword));
        propertyMapper.addMappings(mapper -> mapper.skip(Employee::setQualificationList));
        propertyMapper.addMappings(mapper -> mapper.skip(Employee::setPayrollEntities));
        propertyMapper.addMappings(mapper -> mapper.skip(Employee::setLeaves));
        return modelMapper.map(employeeEntity, Employee.class);
    }
    public User convertUserEntityToUser(UserEntity userEntity) {
        TypeMap<UserEntity, User> propertyMapper = this.modelMapper.createTypeMap(UserEntity.class, User.class);
        propertyMapper.addMappings(mapper -> mapper.skip(User::setAdminPass));
        return modelMapper.map(userEntity, User.class);
    }

}