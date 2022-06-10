package com.example.medicineproject.controller;

import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByName(username));
    }

    @PostMapping("/new")
    public boolean saveUser(@RequestBody UserPostDTO userDTO){
        return userService.save(userDTO);
    }
}
