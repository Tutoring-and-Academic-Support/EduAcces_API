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

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @OneToMany(mappedBy = "tutor")
    private List<CursoTutor> cursoTutores;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private Plan plan;


    // Relacion con User
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "tutor")
    private List<GrupoEstudiantes> grupos;


}

