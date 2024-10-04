package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Puedes agregar métodos personalizados si es necesario
}

