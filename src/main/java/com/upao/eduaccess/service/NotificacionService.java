package com.upao.eduaccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender; // Esta es la importación correcta para JavaMailSender
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarNotificacion(String emailDestino, String asunto, String mensaje) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailDestino);
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);

        mailSender.send(mailMessage);
    }
}

