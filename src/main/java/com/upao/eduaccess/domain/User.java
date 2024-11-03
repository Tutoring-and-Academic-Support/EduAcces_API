package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Relación muchos a uno con Role (determina si es estudiante o tutor)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    // Relación uno a uno con Estudiante (si el usuario es estudiante)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Estudiante estudiante;

    // Relación uno a uno con Tutor (si el usuario es tutor)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tutor tutor;

    public String getEmail() {return email;}
    public String getPassword() {return password;}
}

