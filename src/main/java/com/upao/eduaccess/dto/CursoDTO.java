package com.upao.eduaccess.dto;

import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private String nombreCurso;
    private String descripcion;
    private String estado;
    private int valoracion;
    private int ciclo;
    private String imagen; // Para almacenar la URL de la imagen
}
