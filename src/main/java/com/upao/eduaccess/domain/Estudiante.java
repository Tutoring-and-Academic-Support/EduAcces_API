package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ciclo", nullable = false)
    private int ciclo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCurso> estudianteCursos;

}


