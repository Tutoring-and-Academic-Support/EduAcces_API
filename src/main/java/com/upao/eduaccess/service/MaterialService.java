package com.upao.eduaccess.service;

import com.upao.eduaccess.dto.MaterialDownloadRequestDTO;
import com.upao.eduaccess.dto.MaterialDownloadResponseDTO;

public interface MaterialService {
    MaterialDownloadResponseDTO descargarMaterial(MaterialDownloadRequestDTO request);
}

