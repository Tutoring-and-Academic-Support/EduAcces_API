package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.ComentarioDTO;
import com.upao.eduaccess.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/comentarios")  // Añadimos un prefijo a las rutas para mayor claridad
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Publicar comentario usando ComentarioDTO
    @PostMapping("/publicar")
    public ResponseEntity<String> publicarComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) {
        try {
            String respuesta = comentarioService.publicarComentario(comentarioDTO);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Obtener comentarios de un curso específico
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentariosPorCurso(@PathVariable Long cursoId) {
        List<ComentarioDTO> comentarios = comentarioService.obtenerComentariosPorCurso(cursoId);
        return ResponseEntity.ok(comentarios);
    }

    // Eliminar comentario por ID
    @DeleteMapping("/eliminar/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Long comentarioId) {
        String response = comentarioService.eliminarComentario(comentarioId);
        if (response.equals("Comentario eliminado con éxito.")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
