package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    private final AppointmentRepository appointmentRepository = mock(AppointmentRepository.class);
    private final AppointmentService appointmentService = new AppointmentService(appointmentRepository);

    @Test
    void scheduleAppointment_ShouldSaveAppointment_WhenValidDataIsProvided() {
        Patient patient = new Patient();
        patient.setId(1L);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setAppointmentDate(LocalDateTime.of(2024, 12, 5, 10, 0));
        appointment.setPurpose("Routine Checkup");

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment savedAppointment = appointmentService.scheduleAppointment(appointment);

        assertNotNull(savedAppointment);
        assertEquals(1L, savedAppointment.getPatient().getId());
        assertEquals(LocalDateTime.of(2024, 12, 5, 10, 0), savedAppointment.getAppointmentDate());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }
}
