package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Comentario;
import com.upao.eduaccess.dto.ComentarioEditRequestDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.repository.ComentarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComentarioTutorService {

    private final ComentarioRepository comentarioRepository;

    @Transactional
    public Comentario editComentario(Long comentarioId, ComentarioEditRequestDTO dto) {
        // Buscar el comentario por su ID
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con ID: " + comentarioId));

        // Actualizar el comentario
        comentario.setTexto(dto.getComment());

        // Guardar el comentario actualizado
        return comentarioRepository.save(comentario);
    }
}

