package com.LearnSpringBoot.hospitalManagementSystem;

import com.LearnSpringBoot.hospitalManagementSystem.entity.Appointment;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Insurance;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Patient;
import com.LearnSpringBoot.hospitalManagementSystem.service.AppointmentService;
import com.LearnSpringBoot.hospitalManagementSystem.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional // Automatically rolls back database changes after each test method runs
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        // Uses your seeded Patient 1L
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);

        var newPatient = insuranceService.disaccociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("Cancer")
                .build();

        // Uses your seeded Doctor 1L and Patient 2L
//        var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
//        System.out.println(newAppointment);

        // Uses your seeded Doctor 3L for reassignment
//        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
//        System.out.println(updatedAppointment);
    }
}