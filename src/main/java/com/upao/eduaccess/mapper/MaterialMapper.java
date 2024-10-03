package com.upao.eduaccess.mapper;

import com.upao.eduaccess.dto.MaterialDTO;
import com.upao.eduaccess.domain.Material;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    private final ModelMapper modelMapper;

    // Constructor con inyección de ModelMapper
    public MaterialMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Metodo para convertir de MaterialDTO a Material (entidad)
    public Material toEntity(MaterialDTO materialDTO) {
        return modelMapper.map(materialDTO, Material.class);
    }

    // Metodo para convertir de Material a MaterialDTO
    public MaterialDTO toDTO(Material material) {
        return modelMapper.map(material, MaterialDTO.class);
    }
}

