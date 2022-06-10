package com.example.medicineproject.repository;

import com.example.medicineproject.domain.Role;
import com.example.medicineproject.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository repository;

    @Test
    void findFirstByNameTest() throws Exception {
        User user = new User();
        user.setEmail("rick@gmail.com");
        user.setName("rick");
        user.setRole(Role.CLIENT);
        repository.save(user);
        User user1 = repository.findFirstByName(user.getName()).get();
        assertEquals(user.getName(), user1.getName());
    }

    @Test
    void findFirstByRoleClientTest() throws Exception {
        User user = new User();
        user.setEmail("rick@gmail.com");
        user.setName("rick");
        user.setRole(Role.CLIENT);
        repository.save(user);
        User user1 = repository.findByRole(user.getRole()).get().get(0);
        assertEquals(user.getRole(), user1.getRole());
    }

    @Test
    void findFirstByRoleAdminTest() throws Exception {
        User user = new User();
        user.setEmail("rick1@gmail.com");
        user.setName("rick1");
        user.setRole(Role.ADMIN);
        repository.save(user);
        User user1 = repository.findByRole(user.getRole()).get().get(1);
        assertEquals(user.getRole(), user1.getRole());
    }

    @Test
    void findFirstByRoleDoctorTest() throws Exception {
        User user = new User();
        user.setEmail("rick2@gmail.com");
        user.setName("rick2");
        user.setRole(Role.DOCTOR);
        repository.save(user);
        User user1 = repository.findByRole(user.getRole()).get().get(0);
        assertEquals(user.getRole(), user1.getRole());
    }

}
