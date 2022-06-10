package com.example.medicineproject.integration;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.repository.UserRepository;
import com.example.medicineproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource("/application-test.yaml")
@Sql(value = "/scripts/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MedicineProjectApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void save() {
        UserPostDTO userPostDTO = UserPostDTO.builder().
        username("Nikita")
                .email("Nikita@gmail.com")
                .password("123")
                .matchingPassword("123")
                .build();
        userService.save(userPostDTO);
        User savedUser = userRepository.findFirstByName("Nikita").get();
        assertEquals(savedUser.getId(), 4);
        assertEquals(savedUser.getName(), userPostDTO.getUsername());
        assertEquals(savedUser.getEmail(), userPostDTO.getEmail());
        assertTrue(passwordEncoder.matches(userPostDTO.getPassword(),savedUser.getPassword()));
    }

    @Test
    void getAll() {
        assertEquals(userService.getAll().size(), userRepository.findAll().size());
        System.out.println(userService.getAll().size());
    }

    @Test
    void findByName() {
        UserPostDTO userPostDTO = UserPostDTO.builder().
                username("user")
                .email("mail1@gmail.com")
                .password("pass")
                .matchingPassword("pass")
                .build();
        User savedUser = userService.findByName("user");
        assertEquals(savedUser.getId(), 1);
        assertEquals(savedUser.getName(), userPostDTO.getUsername());
        assertEquals(savedUser.getEmail(), userPostDTO.getEmail());
        assertTrue(passwordEncoder.matches(userPostDTO.getPassword(),savedUser.getPassword()));
    }

    @Test
    void findByRole() {
        UserPostDTO userPostDTO = UserPostDTO.builder().
                username("doctor")
                .email("mail3@gmail.com")
                .password("pass")
                .matchingPassword("pass")
                .build();
        User savedUser = userService.findByRole(Role.DOCTOR).get(0);
        assertEquals(savedUser.getId(), 3);
        assertEquals(savedUser.getName(), userPostDTO.getUsername());
        assertEquals(savedUser.getEmail(), userPostDTO.getEmail());
        assertTrue(passwordEncoder.matches(userPostDTO.getPassword(),savedUser.getPassword()));
    }

    @Test
    void updateProfile() {
        UserPostDTO userPostDTO = UserPostDTO.builder().
                username("user")
                .email("user1@gmail.com")
                .password("pass")
                .matchingPassword("pass")
                .build();

        userService.updateProfile(userPostDTO);
        User savedUser = userService.findByName("user");
        assertEquals(savedUser.getId(), 1);
        assertEquals(savedUser.getName(), userPostDTO.getUsername());
        assertEquals(savedUser.getEmail(), userPostDTO.getEmail());
        assertTrue(passwordEncoder.matches(userPostDTO.getPassword(),savedUser.getPassword()));
    }

}
