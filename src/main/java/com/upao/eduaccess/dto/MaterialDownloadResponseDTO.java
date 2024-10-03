package com.upao.eduaccess.dto;

import lombok.Data;

//Respuesta con la url de descarga

@Data
public class MaterialDownloadResponseDTO {
    private String fileName;
    private String downloadUrl;
    private String fileType;
}
