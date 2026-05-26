package com.LearnSpringBoot.hospitalManagementSystem.repository;

import com.LearnSpringBoot.hospitalManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}