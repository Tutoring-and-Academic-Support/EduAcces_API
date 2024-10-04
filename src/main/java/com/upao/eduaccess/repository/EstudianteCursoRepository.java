package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.EstudianteCurso;
import com.upao.eduaccess.domain.EstudianteCursoPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCursoRepository extends JpaRepository<EstudianteCurso, EstudianteCursoPK> {
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
    
    @Query("SELECT ec.estudiante.id FROM EstudianteCurso ec WHERE ec.curso.id = :cursoId")
    List<Long> findEstudiantesByCursoId(@Param("cursoId") Long cursoId);
}
