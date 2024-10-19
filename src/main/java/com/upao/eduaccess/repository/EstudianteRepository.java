package com.upao.eduaccess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upao.eduaccess.domain.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long>
{
	 Optional<Estudiante> findById(Long id);
}