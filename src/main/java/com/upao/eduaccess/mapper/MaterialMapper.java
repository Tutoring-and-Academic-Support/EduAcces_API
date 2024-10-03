package com.upao.eduaccess.mapper;

import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.dto.MaterialDownloadResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    private final ModelMapper modelMapper;

    public MaterialMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public MaterialDownloadResponseDTO toResponseDTO(Material material){
        MaterialDownloadResponseDTO dto = modelMapper.map(material, MaterialDownloadResponseDTO.class);
        dto.setFileName(material.getTitulo());
        dto.setDownloadUrl(generateDownloadUrl(material.getFilePath()));
        dto.setFileType(material.getTipo().name());
        return dto;
    }

    private String generateDownloadUrl(String filePath) {
        // Aquí puedes implementar la lógica para generar la URL de descarga real
        return "https://tu-servidor.com/descargas/" + filePath;
    }
}
