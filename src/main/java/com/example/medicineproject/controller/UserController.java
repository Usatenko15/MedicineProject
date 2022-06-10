package com.example.medicineproject.controller;

import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserGetDTO;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.mapper.AppointmentMapper;
import com.example.medicineproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public UserController(UserService userService, AppointmentMapper appointmentMapper) {
        this.userService = userService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserPostDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserPostDTO userDTO, Model model){
        if(userService.save(userDTO)){
            return "redirect:/users";
        } else {
            model.addAttribute("user", userDTO);
            return "user";
        }
    }

    @GetMapping("/profile")
    public String profileUser(Principal principal, Model model){
        if(principal ==  null){
            throw new RuntimeException("You are not authorize");
        }
        User user = userService.findByName(principal.getName());

        UserPostDTO dto = new UserPostDTO();
        dto.setUsername(user.getName());
        dto.setPassword(user.getPassword());
        dto.setMatchingPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        model.addAttribute("user", dto);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserPostDTO dto, Principal principal, Model model){
        if(principal ==  null || !Objects.equals(principal.getName(), dto.getUsername())){
            throw new RuntimeException("You are not authorize");
        }
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()
                && !Objects.equals(dto.getPassword(), dto.getMatchingPassword())) {
            model.addAttribute("user", dto);

            return "profile";
        }
        userService.updateProfile(dto);
        return "redirect:/";
    }
}
