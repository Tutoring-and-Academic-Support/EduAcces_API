package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.EstudianteCurso;
import com.upao.eduaccess.domain.EstudianteCursoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCursoRepository extends JpaRepository<EstudianteCurso, EstudianteCursoPK> {
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}
