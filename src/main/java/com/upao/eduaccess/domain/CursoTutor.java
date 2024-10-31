package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "curso_tutor")
public class CursoTutor {

    @EmbeddedId
    private CursoTutorPK id;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_tutor", referencedColumnName = "id_tutor", insertable = false, updatable = false)
    private Tutor tutor;

    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion;

    @Column(name = "fecha_termino", nullable = false)
    private Date fechaTermino;
}

