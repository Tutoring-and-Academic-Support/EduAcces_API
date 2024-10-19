package com.upao.eduaccess.domain;

import com.upao.eduaccess.enums.TipoMaterial;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 50, nullable = false)
    private TipoMaterial tipo;

    @Column(name = "fecha_subida", nullable = false)
    private Date fechaSubida;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
    
    
}

