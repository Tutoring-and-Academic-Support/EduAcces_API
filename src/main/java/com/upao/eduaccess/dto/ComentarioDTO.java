package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ComentarioDTO {

    private Long id;

    @NotBlank(message = "El texto del comentario no puede estar vacío.")
    private String texto;

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long cursoId;

    @NotNull(message = "El ID del estudiante es obligatorio.")
    private Long estudianteId;

    private Date fecha;
}

