package com.LearnSpringBoot.hospitalManagementSystem.repository;

import com.LearnSpringBoot.hospitalManagementSystem.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}