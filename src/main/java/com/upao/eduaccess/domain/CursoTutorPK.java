package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class CursoTutorPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_tutor", referencedColumnName = "id_tutor")
    private Tutor tutor;
}

