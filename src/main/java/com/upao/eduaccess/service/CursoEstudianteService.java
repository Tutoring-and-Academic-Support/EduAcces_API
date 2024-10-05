package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.EstudianteCurso;
import com.upao.eduaccess.dto.CursoEstudianteDTO;
import com.upao.eduaccess.dto.CursoEstudianteResponseDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.exception.UnauthorizedException;
import com.upao.eduaccess.mapper.CursoEstudianteMapper;
import com.upao.eduaccess.repository.CursoRepository;
import com.upao.eduaccess.repository.CursoTutorRepository;
import com.upao.eduaccess.repository.EstudianteCursoRepository;
import com.upao.eduaccess.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CursoEstudianteService {

    @Autowired
    private EstudianteCursoRepository estudianteCursoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoTutorRepository cursoTutorRepository;

    @Autowired
    private CursoEstudianteMapper cursoEstudianteMapper;

    // Metodo para añadir o eliminar estudiantes de un curso usando DTOs
    @Transactional
    public CursoEstudianteResponseDTO modificarAccesoEstudiante(Long tutorId, CursoEstudianteDTO dto) {
        // Verificar permisos del tutor
        if (!cursoTutorRepository.existsByCursoIdAndTutor_IdTutor(dto.getCourseId(), tutorId)) {
            throw new UnauthorizedException("No tienes permiso para modificar este curso.");
        }

        // Verificar si el curso existe
        Curso curso = cursoRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado."));

        // Verificar si el estudiante existe
        Estudiante estudiante = estudianteRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado."));

        // Procesar la acción
        if ("enable".equalsIgnoreCase(dto.getAccess())) {
            if (estudianteCursoRepository.existsByEstudianteIdAndCursoId(dto.getStudentId(), dto.getCourseId())) {
                return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "El estudiante ya está inscrito en el curso.");
            }
            EstudianteCurso estudianteCurso = new EstudianteCurso();
            estudianteCurso.setEstudiante(estudiante);
            estudianteCurso.setCurso(curso);
            estudianteCurso.setFecha(new Date()); // Fecha actual de inscripción
            estudianteCursoRepository.save(estudianteCurso);
            return cursoEstudianteMapper.toResponseDTO(estudianteCurso, "Estudiante añadido al curso con éxito.");
        } else if ("disable".equalsIgnoreCase(dto.getAccess())) {
            if (!estudianteCursoRepository.existsByEstudianteIdAndCursoId(dto.getStudentId(), dto.getCourseId())) {
                return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "El estudiante no está inscrito en el curso.");
            }
            estudianteCursoRepository.deleteByEstudianteIdAndCursoId(dto.getStudentId(), dto.getCourseId());
            return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "Estudiante eliminado del curso con éxito.");
        } else {
            throw new IllegalArgumentException("Acción no válida. Usa 'enable' o 'disable'.");
        }
    }
}
