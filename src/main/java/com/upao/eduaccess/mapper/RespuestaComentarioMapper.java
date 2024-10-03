package com.upao.eduaccess.mapper;

import com.upao.eduaccess.dto.RespuestaComentarioDTO;
import com.upao.eduaccess.domain.Comentario;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RespuestaComentarioMapper {

    // Convertir de DTO `RespuestaComentarioDTO` a entidad `Comentario`
    public Comentario toEntity(RespuestaComentarioDTO respuestaDTO) {
        Comentario comentario = new Comentario();
        comentario.setTexto(respuestaDTO.getRespuesta());
        comentario.setFecha(new Date()); // Asignar la fecha de la respuesta
        comentario.setAutor(respuestaDTO.getAutor());
        // Nota: La asociación con el comentario original (comentario padre) y el curso debe manejarse en el servicio
        return comentario;
    }
}

