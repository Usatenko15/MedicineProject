package com.example.medicineproject.repository;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);

    List<User> findByRole(Role role);
}
