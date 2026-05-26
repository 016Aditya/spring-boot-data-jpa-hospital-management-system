package com.LearnSpringBoot.hospitalManagementSystem.controller;

import com.LearnSpringBoot.hospitalManagementSystem.dto.AppointmentResponseDto;
import com.LearnSpringBoot.hospitalManagementSystem.dto.PatientResponseDto;
import com.LearnSpringBoot.hospitalManagementSystem.service.AppointmentService;
import com.LearnSpringBoot.hospitalManagementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody com.codingshuttle.youtube.hospitalManagement.dto.CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

}