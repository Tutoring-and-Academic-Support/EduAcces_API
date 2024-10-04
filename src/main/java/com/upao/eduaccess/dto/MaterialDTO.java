package com.upao.eduaccess.dto;

import com.upao.eduaccess.enums.TipoMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MaterialDTO {

    private Long id; // Este ID será asignado cuando el material sea creado, útil para futuras referencias

    @NotBlank(message = "El título del material no puede estar vacío.")
    private String titulo; // Título descriptivo del material

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long cursoId; // ID del curso al que pertenece el material

    private Date fechaSubida; // Fecha en la que se subió el material (opcional, generalmente se asigna al momento de crear el material)

    @NotNull(message = "El tipo de material es obligatorio.")
    private TipoMaterial tipo; // Tipo de material (VIDEO, PDF, TEXTO, etc.), usaría el `enum TipoMaterial` definido previamente.

    // Constructor para facilitar la creación de instancias del DTO
    public MaterialDTO() {}

    public MaterialDTO(Long id, String titulo, Long cursoId) {
        this.id = id;
        this.titulo = titulo;
        this.cursoId = cursoId;
        this.fechaSubida = new Date(); // Se asigna la fecha actual al crear el objeto
    }

    // Puedes añadir otros atributos según las necesidades de la aplicación,
    // como la descripción, la URL del archivo, etc.
}

