package com.upao.eduaccess.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upao.eduaccess.enums.TipoMaterial;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 50, nullable = false)
    private TipoMaterial tipo;

    @Column(name = "fecha_subida", nullable = false)
    private Date fechaSubida;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;
}

