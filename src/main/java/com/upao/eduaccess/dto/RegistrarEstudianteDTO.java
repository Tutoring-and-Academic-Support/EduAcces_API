package com.upao.eduaccess.dto;

import jakarta.validation.constraints.Min;
import com.upao.eduaccess.enums.TipoRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegistrarEstudianteDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La contraseña debe tener mínimo 8 caracteres, al menos una letra mayúscula, un número y un símbolo especial."
    )
    private String password;



    private TipoRole role;

    //ESTUDIANTE
    @Min(value = 1, message = "Se ingresó un valor incorrecto.")
    private int ciclo;
}