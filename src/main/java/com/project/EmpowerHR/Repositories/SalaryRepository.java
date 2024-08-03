package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository  extends JpaRepository
        <SalaryEntity, Integer> {}