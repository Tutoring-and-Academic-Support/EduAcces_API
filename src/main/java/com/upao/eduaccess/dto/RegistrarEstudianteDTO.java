package com.upao.eduaccess.dto;

import jakarta.validation.constraints.Min;
import com.upao.eduaccess.enums.TipoRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;


    private TipoRole role;

    //ESTUDIANTE
    @Min(value = 1, message = "Se ingresó un valor incorrecto.")
    private int ciclo;
}