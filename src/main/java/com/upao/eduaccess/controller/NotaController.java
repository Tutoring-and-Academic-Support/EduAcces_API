package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.NotaDTO;
import com.upao.eduaccess.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;

    @PostMapping
    public ResponseEntity<String> createNota(@Valid @RequestBody NotaDTO notaDTO) {
        notaService.create(notaDTO);
        return ResponseEntity.ok("La nota ha sido creada exitosamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNota(@PathVariable Long id, @Valid @RequestBody NotaDTO notaDTO) {
        notaService.update(id, notaDTO);
        return ResponseEntity.ok("La nota ha sido actualizada exitosamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNota(@PathVariable Long id) {
        notaService.delete(id);
        return ResponseEntity.ok("La nota ha sido eliminada exitosamente.");
    }

    @GetMapping("/material/{materialId}")
    public ResponseEntity<List<NotaDTO>> getNotasByMaterialId(@PathVariable Long materialId) {
        List<NotaDTO> notas = notaService.getNotasByMaterialId(materialId);
        return ResponseEntity.ok(notas);
    }
}
