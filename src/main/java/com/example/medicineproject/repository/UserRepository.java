package com.example.medicineproject.repository;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByName(String name);

    Optional<List<User>> findByRole(Role role);
}
