package com.upao.eduaccess.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ComentarioDTO {

    private Long id; 
    
    @NotBlank(message = "El texto del comentario no puede estar vacío.")
    private String texto;

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long cursoId;

    @NotNull(message = "El ID del estudiante es obligatorio.")
    private Long estudianteId;

    private Date fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public Long getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(Long estudianteId) {
		this.estudianteId = estudianteId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
    
}

