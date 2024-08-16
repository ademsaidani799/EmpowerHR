package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.QualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository
        <QualificationEntity, Long> {}