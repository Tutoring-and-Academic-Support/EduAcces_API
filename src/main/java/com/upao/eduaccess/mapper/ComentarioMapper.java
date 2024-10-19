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
        dto.setCursoId(comentario.getCurso().getId());
        return dto;
    }

    public List<ComentarioDTO> toDTOList(List<Comentario> comentarios) {
        return comentarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

