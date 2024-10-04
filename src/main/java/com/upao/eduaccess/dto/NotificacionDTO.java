package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificacionDTO {

	private Long id;

    @NotBlank(message = "El mensaje de la notificación no puede estar vacío.")
    private String mensaje;

    @NotNull(message = "El ID del estudiante es obligatorio.")
    private Long estudianteId;

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long cursoId;

    private boolean leido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Long getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(Long estudianteId) {
		this.estudianteId = estudianteId;
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	} 
    
    
}
