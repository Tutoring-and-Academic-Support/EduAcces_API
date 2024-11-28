package com.upao.eduaccess.mapper;

import com.upao.eduaccess.domain.Estudiante;
import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileDTO toUserProfileDTO(User user) {
        if (user == null) {
            return null;
        }

        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().getName());

        Estudiante estudiante = user.getEstudiante();
        Tutor tutor = user.getTutor();

        if (estudiante != null) {
            dto.setNombre(estudiante.getNombre());
            dto.setApellidos(estudiante.getApellidos());
            dto.setCiclo(estudiante.getCiclo());
        }

        if (tutor != null) {
            dto.setNombre(tutor.getNombre());
            dto.setApellidos(tutor.getApellidos());
            dto.setDepartamento(tutor.getDepartamento());
        }

        return dto;
    }
}
