package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.JobDepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDepartementRepository extends JpaRepository
        <JobDepartmentEntity, Integer> {}