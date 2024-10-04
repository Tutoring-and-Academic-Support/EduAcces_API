package com.upao.eduaccess.service;

import com.upao.eduaccess.dto.ComentarioDTO;
import com.upao.eduaccess.domain.Comentario;
import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.dto.RespuestaComentarioDTO;
import com.upao.eduaccess.exception.ResourceNotFoundException;

import com.upao.eduaccess.repository.ComentarioRepository;
import com.upao.eduaccess.repository.CursoRepository;
import com.upao.eduaccess.repository.EstudianteCursoRepository;
import com.upao.eduaccess.mapper.ComentarioMapper;
import com.upao.eduaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudianteCursoRepository estudianteCursoRepository;

    @Autowired
    private ComentarioMapper comentarioMapper;

    @Autowired
    private NotificacionService notificacionService; // Para enviar notificaciones

    @Autowired
    private UserRepository userRepository; // Para verificar si el estudiante está inscrito


    // Metodo para eliminar comentarios inapropiados
    public String moderarComentario(Long comentarioId, String action, Long tutorId) {
        // Verificar que el comentario existe
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(comentarioId);
        if (comentarioOptional.isEmpty()) {
            return "Comentario no encontrado.";
        }
        Comentario comentario = comentarioOptional.get();

        // Verificar si el tutor tiene permisos sobre el curso del comentario
        Curso curso = comentario.getCurso();
        boolean esTutorDelCurso = curso.getCursoTutores().stream()
                .anyMatch(ct -> ct.getTutor().getIdTutor().equals(tutorId));
        if (!esTutorDelCurso) {
            return "No tienes permiso para gestionar este comentario.";
        }

        // Realizar la acción solicitada
        switch (action.toLowerCase()) {
            case "delete":
                comentarioRepository.deleteById(comentarioId);
                return "Comentario eliminado con éxito.";
            case "approve":
                // Aquí podrías implementar alguna lógica para aprobar el comentario
                return "Comentario aprobado.";
            case "report":
                // Aquí podrías implementar lógica para marcar el comentario como inapropiado
                return "Comentario reportado.";
            default:
                return "Acción no válida.";
        }
    }


    public List<ComentarioDTO> obtenerComentariosPorCurso(Long cursoId) {
        List<Comentario> comentarios = comentarioRepository.findByCursoId(cursoId);
        return comentarioMapper.toDTOList(comentarios);
    }

    public String eliminarComentario(Long comentarioId) {
        Optional<Comentario> comentario = comentarioRepository.findById(comentarioId);
        if (comentario.isPresent()) {
            comentarioRepository.deleteById(comentarioId);
            return "Comentario eliminado con éxito.";
        } else {
            return "Comentario no encontrado.";
        }
    }


    public String responderComentario(RespuestaComentarioDTO respuestaComentarioDTO) {
        // Validar que el comentario exista
        Comentario comentarioExistente = comentarioRepository.findById(respuestaComentarioDTO.getComentarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con id: " + respuestaComentarioDTO.getComentarioId()));

        // Validar que la respuesta no esté vacía
        if (respuestaComentarioDTO.getRespuesta() == null || respuestaComentarioDTO.getRespuesta().trim().isEmpty()) {
            return "El comentario no puede estar vacío.";
        }

        // Validar comentario duplicado
        boolean duplicado = comentarioRepository.existsByTextoAndCursoIdAndAutor(respuestaComentarioDTO.getRespuesta(),
                comentarioExistente.getCurso().getId(), respuestaComentarioDTO.getAutor());
        if (duplicado) {
            return "Este comentario ya ha sido publicado.";
        }

        // Crear la respuesta al comentario
        Comentario respuestaComentario = new Comentario();
        respuestaComentario.setTexto(respuestaComentarioDTO.getRespuesta());
        respuestaComentario.setFecha(new Date());
        respuestaComentario.setCurso(comentarioExistente.getCurso());
        respuestaComentario.setAutor(respuestaComentarioDTO.getAutor()); // Establecer el autor (tutor o estudiante)

        comentarioRepository.save(respuestaComentario);

        return "Respuesta publicada con éxito.";
    }

    //edicon de comentario
    public String editarComentario(Long comentarioId, ComentarioDTO comentarioDTO) {
        // Buscar el comentario por ID
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(comentarioId);

        if (comentarioOptional.isEmpty()) {
            return "Comentario no encontrado.";
        }

        Comentario comentario = comentarioOptional.get();

        // Verificar si el comentario pertenece al estudiante que intenta editarlo
        if (!comentario.getCurso().getId().equals(comentarioDTO.getCursoId())) {
            return "No tienes permiso para editar este comentario.";
        }

        // Validar si el estudiante puede editar el comentario dentro de las 24 horas
        Date ahora = new Date();
        long diff = ahora.getTime() - comentario.getFecha().getTime();
        long horas = diff / (1000 * 60 * 60);

        if (horas > 24) {
            return "El comentario solo puede ser editado dentro de las 24 horas posteriores a su publicación.";
        }

        // Validar la longitud del comentario
        if (comentarioDTO.getTexto().length() > 500) {
            return "El comentario excede el límite de caracteres permitidos.";
        }

        // Actualizar el comentario
        comentario.setTexto(comentarioDTO.getTexto());
        comentarioRepository.save(comentario);

        return "Comentario actualizado con éxito.";
    }


}