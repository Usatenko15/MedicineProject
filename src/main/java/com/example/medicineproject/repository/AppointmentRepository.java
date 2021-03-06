package com.example.medicineproject.repository;

import com.example.medicineproject.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<List<Appointment>> findByClientId(Long id);
}
