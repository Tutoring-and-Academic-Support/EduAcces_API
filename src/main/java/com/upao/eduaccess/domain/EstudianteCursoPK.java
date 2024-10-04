package com.upao.eduaccess.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class EstudianteCursoPK implements Serializable {

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    // Constructor sin argumentos
    public EstudianteCursoPK() {}

    // Constructor con argumentos
    public EstudianteCursoPK(Long idEstudiante, Long idCurso) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
    }
}


