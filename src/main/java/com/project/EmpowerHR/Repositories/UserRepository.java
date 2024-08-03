package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository
        <UserEntity, Integer> {}