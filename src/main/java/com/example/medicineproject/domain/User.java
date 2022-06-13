package com.example.medicineproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@Document
public class User {
    private static final String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean archive = false;
    @Column(name = "isenable", columnDefinition = "boolean default true")
    private boolean isEnable = true;
    @Column(name = "blocktime")
    private LocalDateTime blockTime;
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Appointment> appointment;
}
