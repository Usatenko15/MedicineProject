package com.example.medicineproject.controller;

import com.example.medicineproject.domain.Appointment;
import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.appoitnmentDTO.AppointmentDTO;
import com.example.medicineproject.mapper.AppointmentMapper;
import com.example.medicineproject.repository.AppointmentRepository;
import com.example.medicineproject.service.AppointmentService;
import com.example.medicineproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(UserService userService, AppointmentService appointmentService, AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping
    public String list(Model model) {
        List<AppointmentDTO> list = appointmentService.getAll();
        model.addAttribute("appointments", list);
        return "appointments";
    }

    @GetMapping("/new")
    public String newAppointment(Model model) {
        List<User> doctors = userService.findByRole(Role.DOCTOR);
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointmentDTO", new AppointmentDTO());
        return "appointment";
    }

    @PostMapping("/new")
    public String saveAppointment(AppointmentDTO appointmentDTO){
        System.out.println(appointmentDTO);
        appointmentService.save(appointmentDTO);
        return "redirect:/appointments";
    }
}
