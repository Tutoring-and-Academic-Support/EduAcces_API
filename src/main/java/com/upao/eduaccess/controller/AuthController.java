package com.upao.eduaccess.controller;

import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.dto.AuthResponseDTO;
import com.upao.eduaccess.dto.LoginDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.security.TokenProvider;
import com.upao.eduaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.upao.eduaccess.repository.TutorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private TutorRepository tutorRepository;

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
        User user = userService.findUserByEmail(loginDTO.getEmail());
        String role = userService.findUserByEmail(loginDTO.getEmail()).getRole().getName().toString();
        Long id = user.getId();

        // Determinar el estado de pago si es tutor
        String paymentStatus = "PENDING";
        if (role.equals("TUTOR")) {
            Tutor tutor = tutorRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tutor no encontrado"));
            paymentStatus = (tutor.getPlan() != null) ? "COMPLETED" : "PENDING";
        }

        // Responder con el token, rol y estado de pago
        AuthResponseDTO authResponse = new AuthResponseDTO(token, role, id, paymentStatus);
        return ResponseEntity.ok(authResponse);

    } catch (
    AuthenticationException e) {
        throw new BadCredentialsException("Credenciales inválidas");
    }

    }
    }


