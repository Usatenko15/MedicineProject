package com.example.medicineproject.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostDTO {
    private Long id;
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
}