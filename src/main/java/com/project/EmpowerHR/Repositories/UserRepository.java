package com.project.EmpowerHR.Repositories;

import com.project.EmpowerHR.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository
        <UserEntity, Long> {
    Optional<UserEntity> findByAdminEmail(String email);
}