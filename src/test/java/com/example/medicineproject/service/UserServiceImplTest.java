package com.example.medicineproject.service;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.mapper.AppointmentMapper;
import com.example.medicineproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    AppointmentMapper appointmentMapper;

    @Test
    void save() {
        UserPostDTO userPostDTO = UserPostDTO.builder().
                username("Nikita")
                .email("Nikita@gmail.com")
                .password("123")
                .matchingPassword("123")
                .build();
        Mockito.when(appointmentMapper.toUser(userPostDTO)).thenReturn(new User());
        userService.save(userPostDTO);
        Mockito.verify(userRepository, Mockito.times(1)).save(appointmentMapper.toUser(userPostDTO));
    }

    @Test
    void getAll() {
        UserPostDTO userPostDTO = UserPostDTO.builder().
                username("Nikita")
                .email("Nikita@gmail.com")
                .password("123")
                .matchingPassword("123")
                .build();
        Mockito.when(appointmentMapper.toUser(userPostDTO)).thenReturn(new User());
        userService.save(userPostDTO);
        Mockito.verify(userRepository, Mockito.times(1)).save(appointmentMapper.toUser(userPostDTO));
    }

    @Test
    void findByName() {
        Mockito.when(userRepository.findFirstByName("Nikita")).thenReturn(Optional.of(new User()));
        userService.findByName("Nikita");
        Mockito.verify(userRepository, Mockito.times(1)).findFirstByName("Nikita");
    }

    @Test
    void findByRole() {
        Mockito.when(userRepository.findByRole(Role.CLIENT)).thenReturn(Optional.of(new ArrayList<>()));
        userService.findByRole(Role.CLIENT);
        Mockito.verify(userRepository, Mockito.times(1)).findByRole(Role.CLIENT);
    }
}