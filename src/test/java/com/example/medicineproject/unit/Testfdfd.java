package com.example.medicineproject.unit;

import com.example.medicineproject.controller.UserRestController;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

@WebMvcTest(UserRestController.class)
public class Testfdfd {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserPostDTO userPostDTO;

    @BeforeEach
    void setUp() {
        userPostDTO = UserPostDTO.builder()
                .username("Nikita")
                .email("Nikita@gmail.com")
                .password("123")
                .matchingPassword("123")
                .build();

    }

    @Test
    void save() throws Exception {
        Mockito.when(userService.save(userPostDTO)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/new")
                .content("{\n" +
                        "\t\"username\": \"Hospital 2\",\n" +
                        "\t\"password\" : \"password\",\n" +
                        "\t\"matchingPassword : \"password\",\n" +
                        "\t\"email : \"email@gdas.fdfd\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
