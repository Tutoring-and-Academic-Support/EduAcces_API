package com.upao.eduaccess.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class MaterialDownloadRequestDTO {
    @NotNull(message = "El ID del material es obligatorio.")
    private Long materialId;
}
