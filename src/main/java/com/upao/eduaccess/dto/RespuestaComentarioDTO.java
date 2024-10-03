package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RespuestaComentarioDTO {
    private Long comentarioId; // ID del comentario al que se responde

    @NotBlank(message = "El contenido de la respuesta no puede estar vacío.")
    private String respuesta; // Texto de la respuesta

    @NotBlank(message = "El autor es obligatorio.")
    private String autor; // Autor de la respuesta (nombre del estudiante o tutor)
}
