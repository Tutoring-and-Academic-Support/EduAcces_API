package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Pago;
import com.upao.eduaccess.dto.PagoDTO;
import com.upao.eduaccess.enums.TipoPago;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PagoService {

    private final TutorRepository tutorRepository;

    @Transactional
    public void seleccionarMetodoPago(PagoDTO pagoDTO) {
        //verificar que el método sea válido
        if (pagoDTO.getMetodoPago() == null) {
            throw new ResourceNotFoundException("No ha seleccionado ningún método de pago.");
        }

        //verificar que el método solo sea para paypal y t.credito
        if (pagoDTO.getMetodoPago() != TipoPago.TARJETA_CREDITO && pagoDTO.getMetodoPago() != TipoPago.PAYPAL) {
            throw new ResourceNotFoundException("Método de pago no disponible.");
        }

        //El tutor exista y tenga un plan seleccionado
        tutorRepository.findById(pagoDTO.getTutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Tutor no encontrado con ID: " + pagoDTO.getTutorId()));

        //validaciones sobre el plan seleccionado y la información de pago

        //metedo pago sea valido
        if (pagoDTO.getInformacionPago() == null || pagoDTO.getInformacionPago().isEmpty()) {
            throw new ResourceNotFoundException("Información de pago incorrecta.");
        }

        // procesar y registrar el pago
    }
}
