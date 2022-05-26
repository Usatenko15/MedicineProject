package com.example.medicineproject.service;

import com.example.medicineproject.dto.appoitnmentDTO.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> getAll();
    void save(AppointmentDTO appointmentDTO);
}
