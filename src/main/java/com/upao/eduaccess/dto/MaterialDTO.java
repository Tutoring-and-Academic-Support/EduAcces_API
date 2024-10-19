package com.upao.eduaccess.dto;

import com.upao.eduaccess.enums.TipoMaterial;

public class MaterialDTO {

	private String titulo;
    private TipoMaterial tipo;
    private Long cursoId;
    
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public TipoMaterial getTipo() {
		return tipo;
	}
	public void setTipo(TipoMaterial tipo) {
		this.tipo = tipo;
	}
	public Long getCursoId() {
		return cursoId;
	}
	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}
    
}
