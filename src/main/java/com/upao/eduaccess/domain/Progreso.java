package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "progreso")
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progreso", nullable = false)
    private Long idProgreso;

    @Column(name = "porcentaje", nullable = false)
    private double porcentaje;

    @Column(name = "fecha_ultimo_acceso", nullable = false)
    private Date fechaUltimoAcceso;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante"),
            @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    })
    private EstudianteCurso estudianteCurso;
}
