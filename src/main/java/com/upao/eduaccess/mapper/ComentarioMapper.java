package com.upao.eduaccess.mapper;

import com.upao.eduaccess.dto.ComentarioDTO;
import com.upao.eduaccess.domain.Comentario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComentarioMapper {

    public ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setTexto(comentario.getTexto());
        dto.setFecha(comentario.getFecha());
        dto.setAutor(comentario.getAutor()); // Se supone que `autor` es un campo adicional que tiene `Comentario`.

        return dto;
    }

    public List<ComentarioDTO> toDTOList(List<Comentario> comentarios) {
        return comentarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Convertir de DTO `ComentarioDTO` a entidad `Comentario`
    public Comentario toEntity(ComentarioDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setId(dto.getId());
        comentario.setTexto(dto.getTexto());
        comentario.setFecha(dto.getFecha());
        comentario.setAutor(dto.getAutor());
        // Nota: Las relaciones como `Curso` deben ser manejadas en el servicio, no en el mapper.
        return comentario;
    }
}


