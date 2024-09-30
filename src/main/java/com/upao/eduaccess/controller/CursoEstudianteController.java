package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.CursoEstudianteDTO;
import com.upao.eduaccess.dto.CursoEstudianteResponseDTO;
import com.upao.eduaccess.service.CursoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoEstudianteController {

    @Autowired
    private CursoEstudianteService cursoEstudianteService;

    // Endpoint para añadir o eliminar a un estudiante de un curso
    @PostMapping("/modificar-acceso")
    public ResponseEntity<CursoEstudianteResponseDTO> modificarAccesoEstudiante(
            @RequestParam Long tutorId,
            @RequestBody CursoEstudianteDTO cursoEstudianteDTO) {

        CursoEstudianteResponseDTO respuesta = cursoEstudianteService.modificarAccesoEstudiante(tutorId, cursoEstudianteDTO);
        return ResponseEntity.ok(respuesta);
    }
}



