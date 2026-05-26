package com.LearnSpringBoot.hospitalManagementSystem.service;

import com.LearnSpringBoot.hospitalManagementSystem.entity.Appointment;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Doctor;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Patient;
import com.LearnSpringBoot.hospitalManagementSystem.repository.AppointmentRepository;
import com.LearnSpringBoot.hospitalManagementSystem.repository.DoctorRepository;
import com.LearnSpringBoot.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction. Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if (appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have empty");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // to maintain consistency
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor (Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById (appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor (doctor); //this will automatially call the update coz its dirty now

        doctor.getAppointments().add(appointment); //just for Bidirectional consistency

        return appointment;
    }

}