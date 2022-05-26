package com.example.medicineproject.dto.appoitnmentDTO;

import com.example.medicineproject.dto.UserDTO.UserGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentPostDTO {
    private Long id;
    private UserGetDTO doctor;
    private UserGetDTO client;
}
