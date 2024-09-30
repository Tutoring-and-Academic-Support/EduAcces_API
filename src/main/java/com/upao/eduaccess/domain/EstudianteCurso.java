package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "estudiante_curso")
public class EstudianteCurso {

    @EmbeddedId
    private EstudianteCursoPK id;

    @ManyToOne
    @MapsId("idEstudiante")  // Esto mapea el campo 'idEstudiante' de la clave compuesta
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idCurso")  // Esto mapea el campo 'idCurso' de la clave compuesta
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;

    @Column(name = "fecha", nullable = false)
    private Date fecha;
}



