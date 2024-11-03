package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.AuthResponseDTO;
import com.upao.eduaccess.dto.LoginDTO;
import com.upao.eduaccess.security.TokenProvider;
import com.upao.eduaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
//nuevo
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            // Autenticar al usuario usando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );

            // Generar el token JWT
            String token = tokenProvider.createToken(authentication);

            // Obtener el rol del usuario
            String role = userService.findUserByEmail(loginDTO.getEmail()).getRole().getName().toString();

            // Respuesta con el token y el rol del usuario
            return ResponseEntity.ok(new AuthResponseDTO(token, role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponseDTO("Unauthorized", null));
        }
    }
}
