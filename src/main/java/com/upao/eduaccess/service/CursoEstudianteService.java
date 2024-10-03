package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.EstudianteCurso;
import com.upao.eduaccess.dto.CursoEstudianteDTO;
import com.upao.eduaccess.dto.CursoEstudianteResponseDTO;
import com.upao.eduaccess.mapper.CursoEstudianteMapper;
import com.upao.eduaccess.repository.CursoRepository;
import com.upao.eduaccess.repository.CursoTutorRepository;
import com.upao.eduaccess.repository.EstudianteCursoRepository;
import com.upao.eduaccess.repository.EstudianteRepository;
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
    public CursoEstudianteResponseDTO modificarAccesoEstudiante(Long tutorId, CursoEstudianteDTO dto) {
        // Verificar si la tutora tiene permisos para modificar el curso
        boolean esTutorDelCurso = cursoTutorRepository.existsByCursoIdAndTutor_IdTutor(dto.getCourseId(), tutorId);
        if (!esTutorDelCurso) {
            return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "No tienes permiso para modificar este curso.");
        }

        // Verificar si el curso existe
        Optional<Curso> cursoOptional = cursoRepository.findById(dto.getCourseId());
        if (cursoOptional.isEmpty()) {
            return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "Curso no encontrado.");
        }
        Curso curso = cursoOptional.get();

        // Verificar si el estudiante existe
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(dto.getStudentId());
        if (estudianteOptional.isEmpty()) {
            return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "Estudiante no encontrado.");
        }
        Estudiante estudiante = estudianteOptional.get();

        // Dependiendo de la acción, añadir o eliminar al estudiante del curso
        switch (dto.getAccess().toLowerCase()) {
            case "enable":
                // Añadir al estudiante al curso
                if (estudianteCursoRepository.existsByEstudianteIdAndCursoId(dto.getStudentId(), dto.getCourseId())) {
                    return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "El estudiante ya está inscrito en el curso.");
                }
                EstudianteCurso estudianteCurso = new EstudianteCurso();
                estudianteCurso.setEstudiante(estudiante);
                estudianteCurso.setCurso(curso);
                estudianteCurso.setFecha(new Date()); // Fecha actual de inscripción
                estudianteCursoRepository.save(estudianteCurso);
                return cursoEstudianteMapper.toResponseDTO(estudianteCurso, "Estudiante añadido al curso con éxito.");

            case "disable":
                // Eliminar al estudiante del curso
                if (!estudianteCursoRepository.existsByEstudianteIdAndCursoId(dto.getStudentId(), dto.getCourseId())) {
                    return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "El estudiante no está inscrito en el curso.");
                }
                estudianteCursoRepository.deleteByEstudianteIdAndCursoId(dto.getStudentId(), dto.getCourseId());
                return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "Estudiante eliminado del curso con éxito.");

            default:
                return new CursoEstudianteResponseDTO(dto.getStudentId(), dto.getCourseId(), "Acción no válida. Usa 'enable' o 'disable'.");
        }
    }
}
