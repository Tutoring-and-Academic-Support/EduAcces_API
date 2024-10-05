package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "estudiante_curso")
@Data // Lombok annotation for getter, setter, etc.
public class EstudianteCurso {

    @EmbeddedId
    private EstudianteCursoPK id = new EstudianteCursoPK();

    @ManyToOne
    @MapsId("idEstudiante")  // This maps the idEstudiante in EstudianteCursoPK to the Estudiante entity
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idCurso")  // This maps the idCurso in EstudianteCursoPK to the Curso entity
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;

    @Column(name = "fecha", nullable = false)
    private Date fecha;
}




