package com.example.medicineproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.module.Configuration;

@SpringBootApplication
public class MedicineProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MedicineProjectApplication.class, args);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        System.out.println(encoder.encode("pass"));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!!!");
    }
}
