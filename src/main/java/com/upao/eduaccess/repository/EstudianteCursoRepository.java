package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.EstudianteCurso;
import com.upao.eduaccess.domain.EstudianteCursoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteCursoRepository extends JpaRepository<EstudianteCurso, EstudianteCursoPK> {
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

    // verificar que el estudiante este en el curso
    Optional<EstudianteCurso> findById_IdEstudianteAndId_IdCurso(Long idEstudiante, Long idCurso);
    }