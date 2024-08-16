package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.PayrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository  extends JpaRepository
        <PayrollEntity, Long> {}