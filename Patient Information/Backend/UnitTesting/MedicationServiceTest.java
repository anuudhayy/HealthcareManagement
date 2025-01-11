package com.example.demo.service;

import com.example.demo.entity.Medication;
import com.example.demo.repository.MedicationRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicationServiceTest {

    private final MedicationRepository medicationRepository = mock(MedicationRepository.class);
    private final MedicationService medicationService = new MedicationService(medicationRepository);

    @Test
    void addMedication_ShouldSaveMedication_WhenValidDataIsProvided() {
        Medication medication = new Medication();
        medication.setName("Paracetamol");
        medication.setDosage("500mg");
        medication.setFrequency("Twice a day");
        medication.setStartDate(LocalDate.of(2024, 12, 1));
        medication.setEndDate(LocalDate.of(2024, 12, 10));

        when(medicationRepository.save(any(Medication.class))).thenReturn(medication);

        Medication savedMedication = medicationService.addMedication(medication);

        assertNotNull(savedMedication);
        assertEquals("Paracetamol", savedMedication.getName());
        assertEquals("500mg", savedMedication.getDosage());
        assertEquals("Twice a day", savedMedication.getFrequency());
        verify(medicationRepository, times(1)).save(any(Medication.class));
    }
}
