package com.upao.eduaccess.dto;

public class CambioContraseñaEstudianteDTO {

    private Long id;
    private String nuevaContraseñaEstudiante;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNuevaContraseñaEstudiante() {
        return nuevaContraseñaEstudiante;
    }
    
    public void setNuevaContraseñaEstudiante(String nuevaContraseñaEstudiante) {
        this.nuevaContraseñaEstudiante = nuevaContraseñaEstudiante;
    }
}