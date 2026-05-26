package com.LearnSpringBoot.hospitalManagementSystem.repository;

import com.LearnSpringBoot.hospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}