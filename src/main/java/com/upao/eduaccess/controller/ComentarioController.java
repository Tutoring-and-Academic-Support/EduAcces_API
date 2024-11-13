package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.ComentarioDTO;
import com.upao.eduaccess.dto.RespuestaComentarioDTO;
import com.upao.eduaccess.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Endpoint para moderar comentarios
    @PostMapping("/moderar")
    public ResponseEntity<String> moderarComentario(
            @RequestParam Long comentarioId,
            @RequestParam String action,
            @RequestParam Long tutorId) {

        String respuesta = comentarioService.moderarComentario(comentarioId, action, tutorId);
        return ResponseEntity.ok(respuesta);
    }

    // Publicar comentario usando ComentarioDTO
    @PostMapping("/publicar")
    public ResponseEntity<String> publicarComentario(
            @RequestParam Long estudianteId,
            @RequestParam Long materialId,
            @RequestParam String comentarioTexto) {

        String respuesta = comentarioService.publicarComentarioCurso(estudianteId, materialId, comentarioTexto);
        return ResponseEntity.ok(respuesta);
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

    @PostMapping("/responder")
    public ResponseEntity<String> responderComentario(@RequestBody RespuestaComentarioDTO respuestaComentarioDTO) {
        String respuesta = comentarioService.responderComentario(respuestaComentarioDTO);
        return ResponseEntity.ok(respuesta);
    }


    @PostMapping("/publicarEnMaterial")
    public ResponseEntity<String> publicarComentarioEnMaterial(
            @RequestParam Long estudianteId,
            @RequestParam Long materialId,
            @RequestBody String comentarioTexto) {
        String respuesta = comentarioService.publicarComentarioMaterial(estudianteId, materialId, comentarioTexto);
        return ResponseEntity.ok(respuesta);
    }
}

