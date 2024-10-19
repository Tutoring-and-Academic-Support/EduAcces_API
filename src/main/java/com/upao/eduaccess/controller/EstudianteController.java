package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.CambioContraseñaEstudianteDTO;
import com.upao.eduaccess.dto.CambioNombreUsuarioEstudianteDTO;
import com.upao.eduaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private UserService usuarioService;
    
    @PostMapping("/cambiar-password")
    public ResponseEntity<String> cambiarPassword(@RequestParam Long id, @RequestParam String nuevaPassword) {
        try {
            usuarioService.cambiarPasswordEstudiante(id, nuevaPassword);
            return ResponseEntity.ok("Contraseña cambiada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cambiar-nombre-estudiante")
    public ResponseEntity<String> cambiarNombreUsuarioEstudiante(@RequestBody CambioNombreUsuarioEstudianteDTO dto) {
        usuarioService.cambiarNombreUsuarioEstudiante(dto);
        return ResponseEntity.ok("Nombre de usuario cambiado exitosamente para estudiante");
    }
    
    @PostMapping("/reestablecer-password")
    public ResponseEntity<String> reestablecerPassword(@RequestBody CambioContraseñaEstudianteDTO dto) {
    	if (dto.getNuevaContraseñaEstudiante() == null || dto.getNuevaContraseñaEstudiante().isEmpty()) {
            return ResponseEntity.badRequest().body("La nueva contraseña no puede ser nula o vacía.");
        }

        usuarioService.reestablecerContrasenaEstudiante(dto);
        return ResponseEntity.ok("Contraseña reestablecida exitosamente");
    }
}
