package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoEstudianteResponseDTO {
    private Long studentId;
    private Long courseId;
    private String message;

    public CursoEstudianteResponseDTO(@NotNull(message = "El ID del estudiante es obligatorio.") Long studentId, @NotNull(message = "El ID del curso es obligatorio.") Long courseId, String s) {
    }
    public CursoEstudianteResponseDTO(@NotNull(message = "El ID del estudiante es obligatorio.") Long studentId, @NotNull(message = "El ID del curso es obligatorio.") Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.message = "El estudiante se ha matriculado en el curso.";
    }
}

