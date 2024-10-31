package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.Comentario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByCursoId(Long cursoId);

    boolean existsByTextoAndCursoIdAndAutor(@NotBlank(message = "El contenido de la respuesta no puede estar vacío.") String respuesta, Long id, @NotBlank(message = "El autor es obligatorio.") String autor);
}

