package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ciclo", nullable = false)
    private int ciclo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "contraseña", length = 255, nullable = false)
    private String contraseña;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCurso> estudianteCursos;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
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

	public List<EstudianteCurso> getEstudianteCursos() {
		return estudianteCursos;
	}

	public void setEstudianteCursos(List<EstudianteCurso> estudianteCursos) {
		this.estudianteCursos = estudianteCursos;
	}
}


