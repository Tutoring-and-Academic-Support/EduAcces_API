package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.Role;
import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.dto.RegistrarEstudianteDTO;
import com.upao.eduaccess.dto.RegistrarTutorDTO;
import com.upao.eduaccess.enums.TipoRole;
import com.upao.eduaccess.mapper.RegistrarUsuarioMapper;
import com.upao.eduaccess.repository.RoleRepository;
import com.upao.eduaccess.repository.UserRepository;
import com.upao.eduaccess.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarUsuarioService {
    @Autowired
    private RegistrarUsuarioMapper usuarioMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;



    public void registrarUsuarioEstudiante (RegistrarEstudianteDTO estudianteDTO, String token) {

        if (!validarToken(token)){
            throw new RuntimeException("Token invalido o expirado");
        }

        String email = tokenProvider.getEmailFromToken(token);
        estudianteDTO.setEmail(email);
        estudianteDTO.setRole(TipoRole.ESTUDIANTE);

        existeCorreo(estudianteDTO.getEmail());

        Role role = obtenerRole(TipoRole.ESTUDIANTE);

        User user = usuarioMapper.toUserFromEstudianteDTO(estudianteDTO, role);
        userRepository.save(user);

        Estudiante estudiante = usuarioMapper.toEstudiante(estudianteDTO, user);
        user.setEstudiante(estudiante);
        userRepository.save(user);

    }

    public void registrarUsuarioTutor(RegistrarTutorDTO tutorDTO) {
        existeCorreo(tutorDTO.getEmail());

        if (tutorDTO.getRole() != null && tutorDTO.getRole() != TipoRole.TUTOR) {
            throw new RuntimeException("El rol asignado debe ser TUTOR.");
        }

        //Obtener ROLE DE LA BD
        Role role = obtenerRole(TipoRole.TUTOR);

        User user = usuarioMapper.toUserFromTutorDTO(tutorDTO, role);
        userRepository.save(user);

        Tutor tutor = usuarioMapper.toTutor(tutorDTO, user);
        user.setTutor(tutor);
        userRepository.save(user);
    }

    //METODO VALIDACIONES
    private void existeCorreo (String email){
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El correo ya se encuentra registrado.");
        }
    }

    public boolean validarToken(String token) {
        return tokenProvider.validateToken(token);
    }

    private Role obtenerRole(TipoRole tipoRole){
        return roleRepository.findByName(tipoRole)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
    }
}
