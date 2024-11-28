package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.CursoDTO;
import com.upao.eduaccess.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<CursoDTO> getAllCursos() {
        return cursoService.getAllCursos();
    }

    @GetMapping("/ciclo/{ciclo}")
    public List<CursoDTO> getCursosByCiclo(@PathVariable int ciclo) {
        return cursoService.getCursosByCiclo(ciclo);
    }

    @GetMapping("/{id}")
    public CursoDTO getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id);
    }

    @PostMapping
    public CursoDTO createCurso(@RequestBody CursoDTO cursoDTO) {
        return cursoService.saveCurso(cursoDTO);
    }

    @PutMapping("/{id}")
    public CursoDTO updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        cursoDTO.setId(id);
        return cursoService.saveCurso(cursoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }
}
