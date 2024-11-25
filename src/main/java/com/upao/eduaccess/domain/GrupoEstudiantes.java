package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "grupo_estudiantes")
public class GrupoEstudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // Relación con Tutor (Muchos grupos pueden tener un mismo tutor)
    @ManyToOne
    @JoinColumn(name = "id_tutor", nullable = false)
    private Tutor tutor;

    // Relación con Plan (Muchos grupos pueden tener el mismo plan)
    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false)
    private Plan plan;

    // Relación con Estudiantes (Un grupo tiene una lista de estudiantes)
    @ManyToMany
    @JoinTable(
            name = "grupo_estudiantes_estudiantes",
            joinColumns = @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante")
    )
    private List<Estudiante> estudiantes;
}
