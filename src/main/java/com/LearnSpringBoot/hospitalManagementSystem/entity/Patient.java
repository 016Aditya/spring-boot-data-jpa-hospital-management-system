package com.LearnSpringBoot.hospitalManagementSystem.entity;

import com.LearnSpringBoot.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder            // 👈 FIX 1: Enables Patient.builder() for your tests
@NoArgsConstructor  // 👈 FIX 1: Required by JPA/Hibernate
@AllArgsConstructor // 👈 FIX 1: Required by Lombok's @Builder
@ToString
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthDate"})
        },
        indexes =
        @Index(name = "idx_patient_birthdate", columnList = "birth_date") // 👈 FIX 2: Fixed camelCase to snake_case column name
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")
    @ToString.Exclude // 👈 FIX 3: Prevents bidirectional StackOverflow errors
    private Insurance insurance;

    @Builder.Default  // 👈 Tells Lombok builder not to replace this list with null
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();
}