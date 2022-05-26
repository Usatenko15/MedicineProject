package com.example.medicineproject.mapper;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class Mapper implements AppointmentMapper {

    @Autowired
    @Qualifier("delegate")
    private AppointmentMapper delegate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User toUser(UserPostDTO userPostDTO) {
        // Delegate basic mapping
        User user = delegate.toUser(userPostDTO);

        // Use the repository as needed to set additional mapping
        user = User.builder()
                .name(userPostDTO.getUsername())
                .password(passwordEncoder.encode(userPostDTO.getPassword()))
                .email(userPostDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        return user;
    }
}
