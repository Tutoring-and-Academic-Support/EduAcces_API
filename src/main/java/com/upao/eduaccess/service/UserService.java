package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.dto.CambioContraseñaEstudianteDTO;
import com.upao.eduaccess.dto.CambioContraseñaTutorDTO;
import com.upao.eduaccess.dto.CambioNombreUsuarioEstudianteDTO;
import com.upao.eduaccess.dto.CambioNombreUsuarioTutorDTO;
import com.upao.eduaccess.repository.EstudianteRepository;
import com.upao.eduaccess.repository.TutorRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private TutorRepository tutorRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void cambiarPasswordEstudiante(Long id, String nuevaPassword) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        estudiante.setContraseña(passwordEncoder.encode(nuevaPassword));
        estudianteRepository.save(estudiante);
    }

    public void cambiarPasswordTutor(Long id, String nuevaPassword) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
        tutor.setContraseña(passwordEncoder.encode(nuevaPassword));
        tutorRepository.save(tutor);
    }

    public void cambiarNombreUsuarioEstudiante(CambioNombreUsuarioEstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getId())
        		.orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setNombre(dto.getNuevoNombreUsuario());
        estudianteRepository.save(estudiante);
    }

    public void cambiarNombreUsuarioTutor(CambioNombreUsuarioTutorDTO dto) {
        Tutor tutor = tutorRepository.findById(dto.getId_tutor())
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
        
        tutor.setNombre(dto.getNuevoNombreTutor());
        tutorRepository.save(tutor);
    }
    
    public void reestablecerContrasenaEstudiante(CambioContraseñaEstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        String nuevaContrasenaCodificada = passwordEncoder.encode(dto.getNuevaContraseñaEstudiante());
        estudiante.setContraseña(nuevaContrasenaCodificada);
        estudianteRepository.save(estudiante);
    }

    public void reestablecerContrasenaTutor(CambioContraseñaTutorDTO dto) {
        Tutor tutor = tutorRepository.findById(dto.getId_tutor())
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));

        String nuevaContrasenaCodificada = passwordEncoder.encode(dto.getNuevaContraseñaTutor());
        tutor.setContraseña(nuevaContrasenaCodificada);
        tutorRepository.save(tutor);
    }
}