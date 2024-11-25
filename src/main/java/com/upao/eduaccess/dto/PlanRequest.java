package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlanRequest {

    @NotBlank(message = "El nombre de plan debe ser obligatorio")
    private String nombre;

    @NotBlank(message = "El precio del plan debe ser obligatorio")
    private double precio;

}
