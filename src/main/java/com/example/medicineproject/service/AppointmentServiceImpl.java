package com.example.medicineproject.service;

import com.example.medicineproject.domain.Appointment;
import com.example.medicineproject.domain.AppointmentStatus;
import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.appoitnmentDTO.AppointmentDTO;
import com.example.medicineproject.mapper.AppointmentMapper;
import com.example.medicineproject.repository.AppointmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  UserService userService,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.appointmentMapper = appointmentMapper;
    }
    @Override
    public List<AppointmentDTO> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentClientName = authentication.getName();
        User client = userService.findByName(currentClientName);
        return appointmentRepository.findByClientId(client.getId()).stream()
                .map(appointmentMapper::fromAppointment).collect(Collectors.toList());
    }

    @Override
    public void save(AppointmentDTO appointmentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentClientName = authentication.getName();
        User client = userService.findByName(currentClientName);
        User doctor = userService.findByName(appointmentDTO.getDoctorName());
        if (doctor.getRole()!= Role.DOCTOR)
        { throw new RuntimeException("Не доктор!");}

        Appointment appointment = Appointment.builder()
                .client(client)
                .doctor(doctor)
                .description(appointmentDTO.getDescription())
                .status(AppointmentStatus.NEW)
                .build();
        appointmentRepository.save(appointment);
    }
}
