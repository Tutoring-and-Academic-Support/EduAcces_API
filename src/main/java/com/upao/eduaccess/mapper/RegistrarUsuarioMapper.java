package com.upao.eduaccess.mapper;

import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.Role;
import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.dto.RegistrarEstudianteDTO;
import com.upao.eduaccess.dto.RegistrarTutorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegistrarUsuarioMapper {

    private final PasswordEncoder passwordEncoder; // Inyectamos PasswordEncoder

    public User toUserFromTutorDTO(RegistrarTutorDTO tutorDTO, Role role) {
        User user = new User();
        user.setEmail(tutorDTO.getEmail());
        user.setPassword(passwordEncoder.encode(tutorDTO.getPassword())); // Encriptamos la contraseña
        user.setRole(role);
        return user;
    }

    public Tutor toTutor (RegistrarTutorDTO usuarioDTO, User user){
        Tutor tutor = new Tutor();
        tutor.setUser(user);
        tutor.setNombre(usuarioDTO.getNombre());
        tutor.setApellidos(usuarioDTO.getApellidos());
        tutor.setDepartamento(usuarioDTO.getDepartamento());
        return tutor;
    }

    public Estudiante toEstudiante(RegistrarEstudianteDTO estudianteDTO, User user) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellidos(estudianteDTO.getApellidos());
        estudiante.setUser(user);
        estudiante.setCiclo(estudianteDTO.getCiclo());
        return estudiante;
    }

    public User toUserFromEstudianteDTO(RegistrarEstudianteDTO estudianteDTO, Role role) {
        User user = new User();
        user.setEmail(estudianteDTO.getEmail());
        user.setPassword(passwordEncoder.encode(estudianteDTO.getPassword())); // Encriptamos la contraseña
        user.setRole(role);
        return user;
    }
}
