package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tutor")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor", nullable = false)
    private Long idTutor;

    @Column(name = "departamento", length = 100, nullable = false)
    private String departamento;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "contraseña", length = 255, nullable = false)
    private String contraseña;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<CursoTutor> cursoTutores;

	public Long getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(Long idTutor) {
		this.idTutor = idTutor;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CursoTutor> getCursoTutores() {
		return cursoTutores;
	}

	public void setCursoTutores(List<CursoTutor> cursoTutores) {
		this.cursoTutores = cursoTutores;
	}

}

