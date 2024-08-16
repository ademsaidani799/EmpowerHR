package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository
        <LeaveEntity, Long> {}