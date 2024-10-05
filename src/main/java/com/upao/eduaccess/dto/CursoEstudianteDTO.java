package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoEstudianteDTO {
    @NotNull(message = "El ID del estudiante es obligatorio.")
    private Long studentId;

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long courseId;

    @NotNull(message = "La acción es obligatoria.")
    private String access;  // "enable" para añadir "disable" para eliminar

    public CursoEstudianteDTO(@NotNull(message = "El ID del estudiante es obligatorio.") Long studentId, @NotNull(message = "El ID del curso es obligatorio.") Long courseId, String access) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.access = access;
    }
}

