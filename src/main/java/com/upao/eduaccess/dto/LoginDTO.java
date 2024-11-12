package com.upao.eduaccess.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @Email(message = "Correo electrónico inválido.")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

}
