package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));
    }
    //nuevo
    public void registrarUsuario(User usuario) {
        // Asegurarse de encriptar la contraseña antes de guardar el usuario
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        userRepository.save(usuario);
    }
}
