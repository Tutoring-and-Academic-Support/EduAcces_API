package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre_curso", length = 100, nullable = false)
    private String nombreCurso;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "valoracion", nullable = false)
    private int valoracion;

    @Column(name = "ciclo", nullable = false)
    private int ciclo;

    @OneToMany(mappedBy = "curso")
    private List<Material> materiales;

    @OneToMany(mappedBy = "curso")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "curso")
    private List<CursoTutor> cursoTutores;

    @OneToMany(mappedBy = "curso")
    private List<EstudianteCurso> estudianteCursos;
}

