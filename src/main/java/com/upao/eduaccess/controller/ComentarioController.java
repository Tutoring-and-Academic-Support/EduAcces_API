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
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    } 
    
    @PostMapping("/publicar")
    public ResponseEntity<String> publicarComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) {
        comentarioService.publicarComentario(comentarioDTO);
        return ResponseEntity.ok("Comentario publicado exitosamente");
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentariosPorCurso(@PathVariable Long cursoId) {
        List<ComentarioDTO> comentarios = comentarioService.obtenerComentariosPorCurso(cursoId);
        return ResponseEntity.ok(comentarios);
    }

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

