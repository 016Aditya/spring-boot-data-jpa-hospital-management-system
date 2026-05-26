package com.LearnSpringBoot.hospitalManagementSystem;

import com.LearnSpringBoot.hospitalManagementSystem.dto.BloodGroupCountResponseEntity;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Patient;
import com.LearnSpringBoot.hospitalManagementSystem.entity.type.BloodGroupType;
import com.LearnSpringBoot.hospitalManagementSystem.repository.PatientRepository;
import com.LearnSpringBoot.hospitalManagementSystem.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {
       List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
       System.out.println(patientList);
    }

    @Transactional
    @Test
    @Commit

    public void testTransactionMethods(){

//        Patient patient = patientService.getPatientById(1L);

//        Patient patient = patientRepository.findByName("Diva Patel");

//        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1988, 3, 15),
//                "diya.patel@example.com");

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1995, 3, 14));

//        List<Patient> patientList = patientRepository.findAllPatients();
//
//        for (Patient patient: patientList){
//
//            System.out.println(patient);
//        }
//
//        List<Object[]>bloodGroupList = patientRepository.countEachBloodGroupType();
//        for (Object[] objects:bloodGroupList){
//            System.out.println(objects[0] + " " + objects[1]);
//        }
        // ✅ Test — built-in method already supports pagination
        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(1, 2));

        for (Patient patient : patientPage.getContent()) {
            System.out.println(patient);
        }

        System.out.println("Total pages: " + patientPage.getTotalPages());
        System.out.println("Total elements: " + patientPage.getTotalElements());
//        int rowsUpdated = patientRepository.updateNameWithId("Arav Sharma", 11L);
//        System.out.println(rowsUpdated);

//        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for (BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupList) {
//            System.out.println(bloodGroupCountResponse);
//        }

    }
}
