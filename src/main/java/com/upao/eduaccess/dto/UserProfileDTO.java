package com.upao.eduaccess.dto;

import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.enums.TipoRole;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellidos;
    private String departamento; // Para Tutores
    private Integer ciclo;       // Solo para Estudiantes
    private TipoRole role;
}
