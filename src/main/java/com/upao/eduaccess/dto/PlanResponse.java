package com.upao.eduaccess.dto;

import lombok.Data;

@Data
public class PlanResponse {
    private Integer idPlan;
    private double precio;
    private String nombre;
    private String descripcion;
    private int cantidadEstudiantes; // Añadido este campo
}
