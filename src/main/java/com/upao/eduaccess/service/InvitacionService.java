package com.upao.eduaccess.service;

import com.upao.eduaccess.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitacionService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenProvider tokenProvider;

    public void enviarInvitaciones (List<String> correoEstudiantes){
        for (String email : correoEstudiantes){
            String token = tokenProvider.crearInvitacionToken(email);

            String enlaceInvitacion = "http://localhost:9090/api/registro-completar?token=" + token;

            String mensaje = "Has sido invitado a registrarte." +
                    "Completa tu registro en el siguiente enlace: " + enlaceInvitacion;

            emailService.enviarCorreo(email, "Invitación para registro", mensaje);
        }
    }
}
