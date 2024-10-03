package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ComentarioDTO {

    private Long id;  // El ID puede no ser necesario en la creación, pero útil para obtener o eliminar comentarios

    @NotBlank(message = "El texto del comentario no puede estar vacío.")
    //@Size(max = 1000, message = "El comentario no puede exceder 1000 caracteres")
    private String texto;

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long cursoId;

    @NotNull(message = "El ID del estudiante es obligatorio.")
    private Long estudianteId;

    private Date fecha;
}

