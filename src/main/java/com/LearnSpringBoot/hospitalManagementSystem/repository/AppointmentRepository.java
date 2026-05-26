package com.LearnSpringBoot.hospitalManagementSystem.repository;

import com.LearnSpringBoot.hospitalManagementSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}