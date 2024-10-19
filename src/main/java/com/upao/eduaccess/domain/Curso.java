package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre_curso", length = 100, nullable = false)
    private String nombreCurso;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "valoracion", nullable = false)
    private int valoracion;

    @OneToMany(mappedBy = "curso")
    private List<Material> materiales;

    @OneToMany(mappedBy = "curso")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "curso")
    private List<CursoTutor> cursoTutores;

    @OneToMany(mappedBy = "curso")
    private List<EstudianteCurso> estudianteCursos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public List<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<CursoTutor> getCursoTutores() {
		return cursoTutores;
	}

	public void setCursoTutores(List<CursoTutor> cursoTutores) {
		this.cursoTutores = cursoTutores;
	}

	public List<EstudianteCurso> getEstudianteCursos() {
		return estudianteCursos;
	}

	public void setEstudianteCursos(List<EstudianteCurso> estudianteCursos) {
		this.estudianteCursos = estudianteCursos;
	}


}

