package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.PagoDTO;
import com.upao.eduaccess.enums.TipoPago;
import com.upao.eduaccess.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/seleccionar-metodo")
    public ResponseEntity<String> seleccionarMetodoPago(@Valid @RequestBody PagoDTO pagoDTO) {
        pagoService.seleccionarMetodoPago(pagoDTO);
        return ResponseEntity.ok("Método de pago seleccionado con éxito.");
    }
}
