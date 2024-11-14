package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.InvitacionDTO;
import com.upao.eduaccess.dto.RegistrarEstudianteDTO;
import com.upao.eduaccess.dto.RegistrarTutorDTO;
import com.upao.eduaccess.service.InvitacionService;
import com.upao.eduaccess.service.RegistrarUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrar")
public class RegistrarUsuarioController {
    @Autowired
    private RegistrarUsuarioService usuarioService;

    @Autowired
    private InvitacionService invitacionService; // Corrección aquí

    @PostMapping("/tutor")
    public ResponseEntity<String> registroTutor(@Valid @RequestBody RegistrarTutorDTO tutorDTO) {
        try {
            usuarioService.registrarUsuarioTutor(tutorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tutor registrado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/invitaciones")
    public ResponseEntity<String> enviarInvitacion(@RequestBody InvitacionDTO invitacionDTO) {
        try {
            List<String> correos = invitacionDTO.getCorreos();
            invitacionService.enviarInvitaciones(correos);
            return new ResponseEntity<>("Invitaciones enviadas correctamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al enviar invitaciones: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/registro-completar")
    public ResponseEntity<String> completarRegistro(@RequestParam String token) {
        if (!usuarioService.validarToken(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado.");
        }
        // Aquí puedes devolver información necesaria para el frontend
        return ResponseEntity.ok("Token válido. Procede con el registro.");
    }

    @PostMapping("/estudiante")
    public ResponseEntity<String> registroEstudiante(@Valid @RequestBody RegistrarEstudianteDTO estudianteDTO, @RequestParam String token) {
        try {
            usuarioService.registrarUsuarioEstudiante(estudianteDTO, token);
            return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante registrado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
