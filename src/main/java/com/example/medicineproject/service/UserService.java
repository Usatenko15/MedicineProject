package com.example.medicineproject.service;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserDTO;
import com.example.medicineproject.dto.UserDTO.UserGetDTO;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserPostDTO userDTO);
    List<UserGetDTO> getAll();
    User findByName(String name);
    List<User> findByRole(Role role);
    void updateProfile(UserPostDTO userdto);
}
