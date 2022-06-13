package com.example.medicineproject.repository;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByName(String name);
    Optional<List<User>> findByRole(Role role);
}
