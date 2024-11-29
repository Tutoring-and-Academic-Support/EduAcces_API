package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.MaterialDTO;
import com.upao.eduaccess.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("/nuevo")
    public ResponseEntity<String> agregarNuevoMaterial(@RequestBody MaterialDTO materialDTO) {
        materialService.agregarNuevoMaterial(materialDTO);
        return ResponseEntity.ok("Material agregado y notificaciones enviadas");
    }
}
