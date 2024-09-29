package com.upao.eduaccess.dto;

import com.upao.eduaccess.enums.TipoPago;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoDTO {

    @NotNull(message = "El método de pago es obligatorio.")
    private TipoPago metodoPago;

    @NotNull(message = "El ID del tutor es obligatorio.")
    private Long tutorId;

    @NotNull(message = "El ID del plan es obligatorio.")
    private Long planId;

    private String informacionPago; // Información del pago, por ejemplo: número de tarjeta
}
