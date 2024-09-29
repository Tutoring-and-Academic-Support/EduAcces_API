package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tutor")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor", nullable = false)
    private Long idTutor;

    @Column(name = "departamento", length = 100, nullable = false)
    private String departamento;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "contraseña", length = 255, nullable = false)
    private String contraseña;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<CursoTutor> cursoTutores;

}

