package com.example.medicineproject.mapper;

import com.example.medicineproject.domain.Appointment;
import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserGetDTO;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.dto.appoitnmentDTO.AppointmentDTO;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//@Mapper(componentModel = "spring", uses = PasswordEncoder.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Primary
@DecoratedWith(Mapper.class)
public interface AppointmentMapper {
//    AppointmentMapper MAPPER = Mappers.getMapper(AppointmentMapper.class);


    @Mapping(target = "doctorName", expression = "java(appointment.getDoctor().getName())")
    @Mapping(target = "clientName", expression = "java(appointment.getClient().getName())")
    AppointmentDTO fromAppointment(Appointment appointment);

    @Mapping(target = "username", source = "name")
//    @Mapping(target = "matchingPassword", source = "password")
    UserGetDTO fromUser(User user);

    User toUser(UserPostDTO userDTO);

    @InheritInverseConfiguration
    Appointment toAppointment(AppointmentDTO appointmentDTO);

    List<Appointment> toAppointmentList(List<AppointmentDTO> appointmentDTOS);

    List<AppointmentDTO> fromAppointmentList(List<Appointment> appointments);
}
