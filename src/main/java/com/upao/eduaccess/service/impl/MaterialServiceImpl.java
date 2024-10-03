package com.upao.eduaccess.service.impl;

import com.upao.eduaccess.dto.MaterialDownloadRequestDTO;
import com.upao.eduaccess.dto.MaterialDownloadResponseDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.mapper.MaterialMapper;
import com.upao.eduaccess.repository.MaterialRepository;
import com.upao.eduaccess.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    @Transactional(readOnly = true)
    public MaterialDownloadResponseDTO descargarMaterial(MaterialDownloadRequestDTO request) {
        Material material = materialRepository.findById(request.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("El material no se encuentra disponible"));

        return materialMapper.toResponseDTO(material);
    }
}
