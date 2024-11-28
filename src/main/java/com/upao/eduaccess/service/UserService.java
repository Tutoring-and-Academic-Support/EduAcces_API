package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.dto.UserProfileDTO;
import com.upao.eduaccess.mapper.UserMapper;
import com.upao.eduaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    /**
     * Busca y retorna un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario
     * @return el usuario encontrado
     * @throws RuntimeException si el usuario no se encuentra
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la del usuario.
     * @param rawPassword la contraseña sin encriptar proporcionada por el usuario
     * @param encodedPassword la contraseña encriptada almacenada en la base de datos
     * @return verdadero si la contraseña es correcta, falso en caso contrario
     */
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public UserProfileDTO getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return userMapper.toUserProfileDTO(user);
    }


     // Actualiza el perfil del usuario con los datos proporcionados en el DTO.

    public UserProfileDTO updateUserProfile(String email, UserProfileDTO userProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));

        // Actualiza los datos según el rol del usuario
        if (user.getTutor() != null) {
            user.getTutor().setNombre(userProfileDTO.getNombre());
            user.getTutor().setApellidos(userProfileDTO.getApellidos());
            user.getTutor().setDepartamento(userProfileDTO.getDepartamento());
        } else if (user.getEstudiante() != null) {
            user.getEstudiante().setNombre(userProfileDTO.getNombre());
            user.getEstudiante().setApellidos(userProfileDTO.getApellidos());
            user.getEstudiante().setCiclo(userProfileDTO.getCiclo());
        }

        // Guarda los cambios en la base de datos
        User updatedUser = userRepository.save(user);

        // Devuelve el perfil actualizado como DTO
        return userMapper.toUserProfileDTO(updatedUser);
    }

}
