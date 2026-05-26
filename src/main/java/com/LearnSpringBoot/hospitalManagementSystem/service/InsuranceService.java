package com.LearnSpringBoot.hospitalManagementSystem.service;

import com.LearnSpringBoot.hospitalManagementSystem.repository.InsuranceRepository;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Insurance;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Patient;
import com.LearnSpringBoot.hospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence. EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);

        insurance.setPatient(patient);  //bidirectional consistency maintainance

        return patient;
    }

    // 👈 Added this method to fix the test compilation issue without modifying your code above
    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        Insurance insurance = patient.getInsurance();
        if (insurance != null) {
            insurance.setPatient(null); // bidirectional consistency maintenance
        }
        patient.setInsurance(null);

        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(null);
        return patient;
    }
}