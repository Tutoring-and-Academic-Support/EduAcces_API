package com.upao.eduaccess.service;

import com.upao.eduaccess.security.TokenProvider;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitacionService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenProvider tokenProvider;

    @Value("${app.frontend.base-url}") // Asegúrate de definir esta propiedad
    private String frontendBaseUrl;

    public void enviarInvitaciones(List<String> correoEstudiantes) {
        for (String email : correoEstudiantes) {
            String token = tokenProvider.crearInvitacionToken(email);
            String enlaceInvitacion = frontendBaseUrl + "/registro-estudiante?token=" + token;

            String mensajeHtml = """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        .container {
                            font-family: Arial, sans-serif;
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                            border: 1px solid #e5e5e5;
                            border-radius: 10px;
                            background-color: #f9f9f9;
                        }
                        .button {
                            display: inline-block;
                            background-color: #007bff;
                            color: white;
                            padding: 10px 20px;
                            text-decoration: none;
                            border-radius: 5px;
                            font-size: 16px;
                        }
                        .button:hover {
                            background-color: #0056b3;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>¡Bienvenido a EduAccess!</h1>
                        <p>Hola,</p>
                        <p>Has sido invitado a registrarte en EduAccess. Por favor, completa tu registro haciendo clic en el botón a continuación:</p>
                        <p><a href="%s" class="button">Completar Registro</a></p>
                        <p>Gracias por unirte a nosotros.</p>
                        <p>El equipo de EduAccess</p>
                    </div>
                </body>
                </html>
                """.formatted(enlaceInvitacion);

            try {
                emailService.enviarCorreo(email, "Invitación para registro en EduAccess", mensajeHtml);
            } catch (MessagingException e) {
                throw new RuntimeException("Error al enviar el correo a " + email, e);
            }
        }
    }
}
