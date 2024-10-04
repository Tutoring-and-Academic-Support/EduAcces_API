// src/main/java/com/upao/eduaccess/dto/ComentarioEditRequestDTO.java

package com.upao.eduaccess.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComentarioEditRequestDTO {


    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 500, message = "El comentario no debe exceder los 500 caracteres")
    private String comment;
}
