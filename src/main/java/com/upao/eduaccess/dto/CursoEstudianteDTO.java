package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoEstudianteDTO {

    @NotNull(message = "El ID del estudiante es obligatorio.")
    private Long studentId;  // ID del estudiante

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long courseId;  // ID del curso

    @NotNull(message = "La acción es obligatoria.")
    private String access;  // "enable" para añadir, "disable" para eliminar
}

