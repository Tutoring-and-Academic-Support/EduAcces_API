package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.CursoTutor;
import com.upao.eduaccess.domain.CursoTutorPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoTutorRepository extends JpaRepository<CursoTutor, CursoTutorPK> {
    // Metodo para verificar si el tutor está asociado con el curso
    boolean existsByCursoIdAndTutorId(Long cursoId, Long tutorId);
}

