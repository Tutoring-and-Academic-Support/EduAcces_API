package com.upao.eduaccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upao.eduaccess.dto.CambioContraseñaTutorDTO;
import com.upao.eduaccess.dto.CambioNombreUsuarioTutorDTO;
import com.upao.eduaccess.service.UserService;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

    @Autowired
    private UserService usuarioService;

    @PostMapping("/cambiar-password")
    public ResponseEntity<String> cambiarPassword(@RequestParam Long id, @RequestParam String nuevaPassword) {
        usuarioService.cambiarPasswordTutor(id, nuevaPassword);
        return ResponseEntity.ok("Contraseña cambiada exitosamente");
    }
    @PostMapping("/cambiar-nombre-usuario-tutor")
    public ResponseEntity<String> cambiarNombreUsuarioTutor(@RequestBody CambioNombreUsuarioTutorDTO dto) {
        usuarioService.cambiarNombreUsuarioTutor(dto);
        return ResponseEntity.ok("Nombre de usuario cambiado exitosamente para tutor");
    }
    
    @PostMapping("/reestablecer-password")
    public ResponseEntity<String> reestablecerPassword(@RequestBody CambioContraseñaTutorDTO dto) {
    	if (dto.getNuevaContraseñaTutor() == null || dto.getNuevaContraseñaTutor().isEmpty()) {
            return ResponseEntity.badRequest().body("La nueva contraseña no puede ser nula o vacía.");
        }
    	
    	usuarioService.reestablecerContrasenaTutor(dto);
        return ResponseEntity.ok("Contraseña reestablecida exitosamente");
    }
}

