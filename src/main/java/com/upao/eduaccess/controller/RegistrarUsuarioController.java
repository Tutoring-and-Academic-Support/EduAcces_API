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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registrar")
public class RegistrarUsuarioController {
    @Autowired
    private RegistrarUsuarioService usuarioService;

    @Autowired
    private InvitacionService invitacionService; // Corrección aquí

    @PostMapping("/tutor")
    public ResponseEntity<String> registrarTutor(@RequestBody @Valid RegistrarTutorDTO tutorDTO) {
        try {
            System.out.println("Datos recibidos: " + tutorDTO);

            // Llamamos al servicio para registrar al tutor
            usuarioService.registrarUsuarioTutor(tutorDTO);

            // Devolvemos una respuesta indicando que el registro fue exitoso
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Registro exitoso. Ahora puedes iniciar sesión.");
        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error, devolvemos un mensaje adecuado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar el tutor: " + e.getMessage());
        }
    }

    @PostMapping("/invitaciones")
    public ResponseEntity<Map<String, String>> enviarInvitacion(@RequestBody InvitacionDTO invitacionDTO) {
        try {
            List<String> correos = invitacionDTO.getCorreos();
            invitacionService.enviarInvitaciones(correos);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invitaciones enviadas correctamente.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al enviar invitaciones: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
