package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class NotaDTO {
    private Long id;

    @NotBlank(message = "La nota no puede estar vacía.")
    @Size(max = 1000, message = "La nota no puede exceder los 1000 caracteres.")
    private String texto;

    private Date fecha; // La fecha se asignará automáticamente

    private Long materialId;
}
