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

    private Long idEstudiante;

    private Long idCurso;
}


