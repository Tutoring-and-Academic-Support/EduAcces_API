package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "texto", columnDefinition = "TEXT", nullable = false)
    private String texto;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "autor", nullable = false)
    private String autor; // Agregado: nombre del autor del comentario (estudiante o tutor)

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "comentario_padre_id", referencedColumnName = "id")
    private Comentario comentarioPadre; // Agregado: referencia al comentario padre si es una respuesta

    @OneToMany(mappedBy = "comentarioPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> respuestas; // Agregado: lista de respuestas asociadas a este comentario

    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    private Estudiante estudiante; // Relación con el estudiante que hizo el comentario
}
