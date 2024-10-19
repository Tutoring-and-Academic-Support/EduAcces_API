package com.upao.eduaccess.dto;

public class CambioNombreUsuarioEstudianteDTO {
    private Long id;
    private String nuevoNombreUsuario;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNuevoNombreUsuario() {
        return nuevoNombreUsuario;
    }

    public void setNuevoNombreUsuario(String nuevoNombreUsuario) {
        this.nuevoNombreUsuario = nuevoNombreUsuario;
    }
}