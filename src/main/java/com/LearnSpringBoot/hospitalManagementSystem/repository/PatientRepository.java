package com.LearnSpringBoot.hospitalManagementSystem.repository;

import com.LearnSpringBoot.hospitalManagementSystem.dto.BloodGroupCountResponseEntity;
import com.LearnSpringBoot.hospitalManagementSystem.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.LearnSpringBoot.hospitalManagementSystem.entity.type.BloodGroupType;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findByNameContaining(String query);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    List<Patient> findByBloodGroup(BloodGroupType bloodGroup);


    @Query("select p from Patient p where p.birthDate> :birthDate")
    List<Patient> findByBornAfterDate (@Param("birthDate") LocalDate birthDate);

    @Query("Select new com.LearnSpringBoot.hospitalManagementSystem.dto.BloodGroupCountResponseEntity(p.bloodGroup, Count (p)) from Patient p group by p.bloodGroup")
    //    List<Object[]> countEachBloodGroupType();
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "select * from patient", nativeQuery = true)
    List<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("Update Patient p Set p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);



}
