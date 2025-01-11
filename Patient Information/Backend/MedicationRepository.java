package com.example.appointmentSystem.repository;

import com.example.appointmentSystem.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
