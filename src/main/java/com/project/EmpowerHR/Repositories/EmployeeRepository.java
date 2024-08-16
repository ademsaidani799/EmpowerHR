package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository
        <EmployeeEntity, Long> {
    boolean existsByEmail(String email);
}
