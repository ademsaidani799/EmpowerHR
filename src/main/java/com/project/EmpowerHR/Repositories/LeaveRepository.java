package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.LeaveEnitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository
        <LeaveEnitiy, Integer> {}