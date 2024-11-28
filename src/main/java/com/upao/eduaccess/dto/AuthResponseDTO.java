package com.upao.eduaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String role;
    private Long id;
    private String paymentStatus;
}
