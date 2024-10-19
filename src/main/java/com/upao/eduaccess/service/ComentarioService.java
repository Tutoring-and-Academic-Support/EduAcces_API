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

    // Metodo para publicar comentarios en un curso
    public String publicarComentarioCurso(Long estudianteId, Long cursoId, String comentarioTexto) {
        // Verificar si el estudiante está inscrito en el curso
        boolean estaInscrito = estudianteCursoRepository.existsByEstudianteIdAndCursoId(estudianteId, cursoId);
        if (!estaInscrito) {
            return "No estás inscrito en este curso.";
        }

        // Validar la longitud del comentario
        if (comentarioTexto.length() > 500) {
            return "El comentario excede el límite de caracteres permitidos (500 caracteres).";
        }

        // Validar contenido ofensivo
        if (contieneContenidoOfensivo(comentarioTexto)) {
            return "El comentario contiene contenido ofensivo y no puede ser publicado.";
        }

        // Crear el comentario
        Comentario comentario = new Comentario();
        comentario.setTexto(comentarioTexto);
        comentario.setFecha(new Date());

        // Asociar el comentario con el curso
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isEmpty()) {
            return "Curso no encontrado.";
        }
        Curso curso = cursoOptional.get();
        comentario.setCurso(curso);

        // Guardar el comentario en la base de datos
        comentarioRepository.save(comentario);

        // Obtener el correo del tutor y enviar la notificación
        String emailTutor = curso.getCursoTutores().get(0).getTutor().getUser().getEmail();  // Asume que el curso tiene al menos un tutor
        notificacionService.enviarNotificacion(
                emailTutor,
                "Nuevo comentario publicado",
                "Se ha publicado un nuevo comentario en uno de tus cursos."
        );

        return "Comentario publicado con éxito.";
    }


    // Metodo básico para validar contenido ofensivo (puedes usar un diccionario o una API externa)
    private boolean contieneContenidoOfensivo(String texto) {
        String[] palabrasOfensivas = { "malaPalabra1", "malaPalabra2" }; // Agrega palabras o usa una API
        for (String palabra : palabrasOfensivas) {
            if (texto.toLowerCase().contains(palabra.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

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

    public String publicarComentario(ComentarioDTO comentarioDTO) {
        // Validar la longitud del comentario
        if (comentarioDTO.getTexto().length() > 500) {
            return "El comentario excede el límite de caracteres permitidos.";
        }

        // Validar si el estudiante está inscrito en el curso
        boolean estaInscrito = estudianteCursoRepository.existsByEstudianteIdAndCursoId(comentarioDTO.getEstudianteId(), comentarioDTO.getCursoId());
        if (!estaInscrito) {
            return "No estás inscrito en este curso.";
        }

        // Buscar el curso
        Optional<Curso> cursoOptional = cursoRepository.findById(comentarioDTO.getCursoId());
        if (cursoOptional.isEmpty()) {
            return "Curso no encontrado.";
        }

        Curso curso = cursoOptional.get();

        // Crear el comentario
        Comentario comentario = new Comentario();
        comentario.setTexto(comentarioDTO.getTexto());
        comentario.setFecha(new Date());
        comentario.setCurso(curso);

        // Guardar el comentario
        comentarioRepository.save(comentario);
        notificacionService.enviarNotificacion(
                "tutora@ejemplo.com",
                "Nuevo comentario publicado",
                "Se ha publicado un nuevo comentario en uno de tus cursos."
        );
        return "Comentario publicado con éxito.";
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
}

