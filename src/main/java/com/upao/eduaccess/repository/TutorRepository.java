package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    boolean existsTutorByEmail(String email);
    Tutor findTutorByEmailAndContraseña(String email, String contraseña);
}

