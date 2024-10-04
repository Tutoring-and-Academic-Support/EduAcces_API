package com.upao.eduaccess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upao.eduaccess.domain.Material;
import com.upao.eduaccess.dto.NotificacionDTO;
import com.upao.eduaccess.service.NotificacionService;

@RestController
@RequestMapping("/api/notificacion")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<NotificacionDTO>> obtenerNotificacionesPorEstudiante(@PathVariable Long estudianteId) {
        List<NotificacionDTO> notificaciones = notificacionService.obtenerNotificacionesPorEstudiante(estudianteId);
        return ResponseEntity.ok(notificaciones);
    }

    @PutMapping("/{notificacionId}/leido")
    public ResponseEntity<String> marcarComoLeido(@PathVariable Long notificacionId) {
        notificacionService.marcarComoLeido(notificacionId);
        return ResponseEntity.ok("Notificación marcada como leída");
    }

    @DeleteMapping("/{notificacionId}")
    public ResponseEntity<String> eliminarNotificacion(@PathVariable Long notificacionId) {
        notificacionService.eliminarNotificacion(notificacionId);
        return ResponseEntity.ok("Notificación eliminada");
    }
}
