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

}
