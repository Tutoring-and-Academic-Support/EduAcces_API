package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.EstudianteCurso;
import com.upao.eduaccess.domain.EstudianteCursoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(exported = false)
@Repository
public interface EstudianteCursoRepository extends JpaRepository<EstudianteCurso, EstudianteCursoPK> {

    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

    void deleteByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

    // Buscar todas las inscripciones de un estudiante
    List<EstudianteCurso> findByEstudianteId(Long estudianteId);

    // Buscar todas las inscripciones a un curso
    List<EstudianteCurso> findByCursoId(Long cursoId);
}