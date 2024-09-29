package com.upao.eduaccess.mapper;

import com.upao.eduaccess.dto.NotaDTO;
import com.upao.eduaccess.domain.Nota;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    private final ModelMapper modelMapper;

    public NotaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NotaDTO toDTO(Nota nota) {
        return modelMapper.map(nota, NotaDTO.class);
    }

    public Nota toEntity(NotaDTO notaDTO) {
        return modelMapper.map(notaDTO, Nota.class);
    }
}
