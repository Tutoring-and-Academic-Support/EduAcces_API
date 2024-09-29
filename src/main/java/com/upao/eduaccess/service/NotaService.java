package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.domain.Nota;
import com.upao.eduaccess.dto.NotaDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.mapper.NotaMapper;
import com.upao.eduaccess.repository.MaterialRepository;
import com.upao.eduaccess.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final MaterialRepository materialRepository;
    private final NotaMapper notaMapper;

    @Transactional
    public NotaDTO create(NotaDTO notaDTO) {
        Material material = materialRepository.findById(notaDTO.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con id: " + notaDTO.getMaterialId()));

        Nota nota = notaMapper.toEntity(notaDTO);
        nota.setMaterial(material);
        nota.setFecha(new Date()); // Asigna la fecha actual

        Nota savedNota = notaRepository.save(nota);
        return notaMapper.toDTO(savedNota);
    }

    @Transactional
    public NotaDTO update(Long id, NotaDTO notaDTO) {
        Nota existingNota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con id: " + id));

        existingNota.setTexto(notaDTO.getTexto()); // Actualizar texto
        existingNota.setFecha(new Date()); //

        Nota updatedNota = notaRepository.save(existingNota);
        return notaMapper.toDTO(updatedNota);
    }

    @Transactional
    public void delete(Long id) {
        Nota existingNota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con id: " + id));
        notaRepository.delete(existingNota);
    }

    public List<NotaDTO> getNotasByMaterialId(Long materialId) {
        List<Nota> notas = notaRepository.findByMaterialId(materialId);
        return notas.stream().map(notaMapper::toDTO).toList();
    }
}
