package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.dto.MaterialDTO;
import com.upao.eduaccess.dto.NotificacionDTO;
import com.upao.eduaccess.repository.EstudianteCursoRepository;
import com.upao.eduaccess.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EstudianteCursoRepository estudianteCursoRepository;

    @Autowired
    private NotificacionService notificacionService;

    public void agregarNuevoMaterial(MaterialDTO materialDTO) {
        Material material = new Material();
        material.setTitulo(materialDTO.getTitulo());
        material.setTipo(materialDTO.getTipo());
        material.setFechaSubida(new Date(System.currentTimeMillis()));

        Curso curso = new Curso();
        curso.setId(materialDTO.getCursoId());
        material.setCurso(curso);

        materialRepository.save(material);

        List<Long> estudiantesIds = estudianteCursoRepository.findEstudiantesByCursoId(materialDTO.getCursoId());

        for (Long estudianteId : estudiantesIds) {
            NotificacionDTO notificacionDTO = new NotificacionDTO();
            notificacionDTO.setMensaje("Se ha publicado nuevo material: " + materialDTO.getTitulo());
            notificacionDTO.setEstudianteId(estudianteId);
            notificacionDTO.setCursoId(materialDTO.getCursoId());
            notificacionDTO.setLeido(false); 
            notificacionService.enviarNotificacion(notificacionDTO);
    }
    }
}