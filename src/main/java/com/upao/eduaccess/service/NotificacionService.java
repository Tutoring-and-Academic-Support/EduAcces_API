package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.domain.Notificacion;
import com.upao.eduaccess.dto.NotificacionDTO;
import com.upao.eduaccess.repository.CursoRepository;
import com.upao.eduaccess.repository.EstudianteRepository;
import com.upao.eduaccess.repository.NotificacionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {
 

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository; 

    @Autowired
    private NotificacionRepository notificacionRepository;

    public void enviarNotificacion(NotificacionDTO notificacionDTO) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(notificacionDTO.getMensaje());

        Estudiante estudiante = estudianteRepository.findById(notificacionDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Curso curso = cursoRepository.findById(notificacionDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        notificacion.setEstudiante(estudiante);
        notificacion.setCurso(curso);
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setLeido(notificacionDTO.isLeido());

        notificacionRepository.save(notificacion);
    }

    public void marcarComoLeido(Long notificacionId) {

        Notificacion notificacion = notificacionRepository.findById(notificacionId)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        notificacion.setLeido(true);
        notificacionRepository.save(notificacion);
    }
    
    public List<NotificacionDTO> obtenerNotificacionesPorEstudiante(Long estudianteId) {
        List<Notificacion> notificaciones = notificacionRepository.findByEstudianteId(estudianteId);
        return notificaciones.stream()
                .map(this::convertirADTO) 
                .collect(Collectors.toList());
    }
    
    public void eliminarNotificacion(Long notificacionId) {
        notificacionRepository.deleteById(notificacionId);
    }
    

    private NotificacionDTO convertirADTO(Notificacion notificacion) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(notificacion.getId());
        dto.setMensaje(notificacion.getMensaje());
        dto.setEstudianteId(notificacion.getEstudiante().getId());
        dto.setCursoId(notificacion.getCurso().getId());
        dto.setLeido(notificacion.isLeido());
        return dto;
    }
}

