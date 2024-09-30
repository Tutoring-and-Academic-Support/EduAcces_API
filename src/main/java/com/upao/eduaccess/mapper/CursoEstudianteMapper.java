package com.upao.eduaccess.mapper;

import com.upao.eduaccess.dto.CursoEstudianteDTO;
import com.upao.eduaccess.dto.CursoEstudianteResponseDTO;
import com.upao.eduaccess.domain.EstudianteCurso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CursoEstudianteMapper {

    private final ModelMapper modelMapper;

    public CursoEstudianteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convertir CursoEstudianteDTO a EstudianteCurso (entidad)
    public EstudianteCurso toEntity(CursoEstudianteDTO dto) {
        return modelMapper.map(dto, EstudianteCurso.class);
    }

    // Convertir de EstudianteCurso a CursoEstudianteResponseDTO
    public CursoEstudianteResponseDTO toResponseDTO(EstudianteCurso estudianteCurso, String message) {
        CursoEstudianteResponseDTO responseDTO = new CursoEstudianteResponseDTO();
        responseDTO.setStudentId(estudianteCurso.getId().getEstudiante().getId());
        responseDTO.setCourseId(estudianteCurso.getId().getCurso().getId());
        responseDTO.setMessage(message);
        return responseDTO;
    }
}

