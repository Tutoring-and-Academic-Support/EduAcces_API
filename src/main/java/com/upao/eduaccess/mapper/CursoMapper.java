package com.upao.eduaccess.mapper;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.dto.CursoDTO;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO toDto(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNombreCurso(curso.getNombreCurso());
        dto.setDescripcion(curso.getDescripcion());
        dto.setEstado(curso.getEstado());
        dto.setValoracion(curso.getValoracion());
        dto.setCiclo(curso.getCiclo());
        dto.setImagen("ruta/de/la/imagen/" + curso.getId()); // Cambia según tu lógica
        return dto;
    }

    public Curso toEntity(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setId(dto.getId());
        curso.setNombreCurso(dto.getNombreCurso());
        curso.setDescripcion(dto.getDescripcion());
        curso.setEstado(dto.getEstado());
        curso.setValoracion(dto.getValoracion());
        curso.setCiclo(dto.getCiclo());
        return curso;
    }
}
