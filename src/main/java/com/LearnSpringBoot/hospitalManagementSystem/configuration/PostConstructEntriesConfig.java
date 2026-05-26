//package com.LearnSpringBoot.hospitalManagementSystem.configuration;
//
//import com.LearnSpringBoot.hospitalManagementSystem.entity.Appointment;
//import com.LearnSpringBoot.hospitalManagementSystem.entity.Doctor;
//import com.LearnSpringBoot.hospitalManagementSystem.entity.Patient;
//import com.LearnSpringBoot.hospitalManagementSystem.repository.DoctorRepository;
//import com.LearnSpringBoot.hospitalManagementSystem.repository.PatientRepository;
//import com.LearnSpringBoot.hospitalManagementSystem.service.AppointmentService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import java.time.LocalDateTime;
//
//@Configuration
//public class PostConstructEntriesConfig {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    @PostConstruct
//    void appointmentEntry() {
//        // 1. Create and save a dummy Doctor first
//        Doctor doctor = new Doctor();
//        doctor.setName("Dr. John Doe");
//        doctor.setSpecialization("Cardiology");
//        doctor = doctorRepository.save(doctor);
//
//        // 2. Create and save a dummy Patient
//        Patient patient = new Patient();
//        patient.setName("Jane Smith");
//        patient = patientRepository.save(patient);
//
//        // 3. Create the Appointment object details
//        Appointment appointment = new Appointment();
//        appointment.setAppointmentTime(LocalDateTime.now().plusDays(1));
//        // Add any other required fields for your Appointment entity here
//
//        // 4. Pass all 3 required arguments to the service method
//        appointmentService.createNewAppointment(appointment, doctor.getId(), patient.getId());
//    }
//}