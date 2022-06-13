package com.example.medicineproject.service;

import com.example.medicineproject.config.security.LoginAttemptService;
import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import com.example.medicineproject.dto.UserDTO.UserGetDTO;
import com.example.medicineproject.dto.UserDTO.UserPostDTO;
import com.example.medicineproject.mapper.AppointmentMapper;
import com.example.medicineproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AppointmentMapper appointmentMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public boolean save(UserPostDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())){
            throw new RuntimeException("fdsfdsfdsfds");
        }
        User user = appointmentMapper.toUser(userDTO);
        user.setEnable(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserGetDTO> getAll() {
        return userRepository.findAll().stream()
                .map(appointmentMapper::fromUser).collect(Collectors.toList());
    }

    @Override
    public List<UserGetDTO> getAllBlocked() {
        return userRepository.findAll().stream().filter(user ->
                        user.getBlockTime()!=null && Math.abs(Duration.between(LocalDateTime.now(), user.getBlockTime()).getSeconds())<=300 )
                .map(appointmentMapper::fromUser).collect(Collectors.toList());
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name).orElseThrow(RuntimeException:: new);
    }

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role).orElseThrow(RuntimeException:: new);
    }

    @Override
    public void updateProfile(UserPostDTO userdto) {
        User savedUser = userRepository.findFirstByName(userdto.getUsername()).orElseThrow(RuntimeException:: new);
//        if (savedUser.isPresent()) {
//            throw new RuntimeException("User not found b name" + userdto.getUsername());
//        }

        boolean isChanged = false;
        if (userdto.getPassword() != null && !userdto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userdto.getPassword()));
            isChanged = true;
        }

        if(!Objects.equals(userdto.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(userdto.getEmail());
            isChanged = true;
        }

        if (isChanged) {
            userRepository.save(savedUser);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        System.out.println(ip);
        User user = userRepository.findFirstByName(username).orElseThrow(RuntimeException:: new);
        if (user.getBlockTime()==null || Math.abs(Duration.between(LocalDateTime.now(), user.getBlockTime()).getSeconds())>=300){
            if (loginAttemptService.isBlocked(ip)) {
                user.setBlockTime(LocalDateTime.now());
                userRepository.save(user);
                throw new RuntimeException("blocked");
            }
        }
        else {
            throw new RuntimeException("blocked");
        }

//        User user = userRepository.findFirstByName(username).orElseThrow(RuntimeException:: new);
        if(user == null) {
            throw new UsernameNotFoundException("nima");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
