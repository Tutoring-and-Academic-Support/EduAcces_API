package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.MaterialDownloadRequestDTO;
import com.upao.eduaccess.dto.MaterialDownloadResponseDTO;
import com.upao.eduaccess.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("/descargar")
    public ResponseEntity<MaterialDownloadResponseDTO> descargarMaterial(
            @Valid @RequestBody MaterialDownloadRequestDTO request) {
        MaterialDownloadResponseDTO response = materialService.descargarMaterial(request);
        return ResponseEntity.ok(response);
    }

   /* Descragar material
   @PostMapping("/descargar")
    public ResponseEntity<MaterialDownloadResponseDTO> descargarMaterial(
            @Validated @RequestBody MaterialDownloadRequestDTO request) {
        MaterialDownloadResponseDTO response = materialService.descargarMaterial(request);
        return ResponseEntity.ok(response);
        }*/

}
