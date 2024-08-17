package com.project.EmpowerHR.Configs;

import com.project.EmpowerHR.DTOs.*;
import com.project.EmpowerHR.Entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Skip null values during mapping
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.typeMap(EmployeeEntity.class, EmployeeDto.class).addMappings(mapper -> {
            mapper.skip(EmployeeDto::setPassword);
        });
        modelMapper.typeMap(UserEntity.class, UserDto.class).addMappings(mapper -> {
            mapper.skip(UserDto::setAdminPass);
        });
        // Configure mapping for EmployeeDTO to Employee to avoid infinite recursion
        modelMapper.addMappings(new PropertyMap<EmployeeEntity, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setQualificationList(null); // Exclude department mapping
                map().setPayrollEntities(null); // Exclude department mapping
                map().setLeaves(null); // Exclude department mapping
            }
        });
        modelMapper.addMappings(new PropertyMap<JobDepartmentEntity, JobDepartmentDto>() {
            @Override
            protected void configure() {
                map().setSalaryEntityList(null); // Exclude department mapping
                map().setPayrollEntityList(null); // Exclude department mapping
            }
        });
        modelMapper.addMappings(new PropertyMap<LeaveEntity, LeaveDto>() {
            @Override
            protected void configure() {
                map().setPayrollEntityList(null); // Exclude department mapping
            }
        });
        modelMapper.addMappings(new PropertyMap<SalaryEntity, SalaryDto>() {
            @Override
            protected void configure() {
                map().setPayrollEntityList(null); // Exclude department mapping
            }
        });
        return modelMapper;
    }
}