package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.dto.MaterialDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.mapper.MaterialMapper;
import com.upao.eduaccess.repository.CursoRepository;
import com.upao.eduaccess.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, CursoRepository cursoRepository, MaterialMapper materialMapper, NotificacionService notificacionService) {
        this.materialRepository = materialRepository;
        this.cursoRepository = cursoRepository;
        this.materialMapper = materialMapper;
        this.notificacionService = notificacionService;
    }

    @Transactional
    public MaterialDTO agregarNuevoMaterial(MaterialDTO materialDTO) {
        // Convertir el DTO a una entidad para guardarlo
        Material material = materialMapper.toEntity(materialDTO);

        // Verificar si el curso asociado al material existe
        Curso curso = cursoRepository.findById(materialDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + materialDTO.getCursoId()));

        // Asociar el curso al material
        material.setCurso(curso);
        material.setFechaSubida((Date) materialDTO.getFechaSubida()); // Asignar la fecha actual

        // Guardar el material en la base de datos
        Material savedMaterial = materialRepository.save(material);

        // Enviar la notificación al tutor (igual que en la respuesta anterior)
        String emailTutor = curso.getCursoTutores().stream()
                .findFirst()
                .map(t -> t.getTutor().getEmail())
                .orElse(null);

        if (emailTutor != null) {
            String mensaje = String.format("Nuevo material agregado al curso '%s': %s. Fecha de subida: %s. ",
                    curso.getNombreCurso(), savedMaterial.getTitulo(), savedMaterial.getFechaSubida());

            String linkMaterial = "https://eduaccess.com/material/" + savedMaterial.getId(); // Ejemplo de enlace
            mensaje += "Puedes revisarlo aquí: " + linkMaterial;

            try {
                notificacionService.enviarNotificacion(emailTutor, "Nuevo Material Agregado", mensaje);
                System.out.println("La notificación fue enviada exitosamente al correo registrado.");
            } catch (Exception e) {
                System.err.println("El correo no fue enviado debido a un error en la dirección de correo electrónico.");
            }
        }

        // Convertir la entidad guardada nuevamente a DTO para la respuesta
        return materialMapper.toDTO(savedMaterial);
    }
}


