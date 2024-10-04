package com.upao.eduaccess.service;

import com.upao.eduaccess.dto.ComentarioDTO;
import com.upao.eduaccess.domain.Comentario;
import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.repository.ComentarioRepository;
import com.upao.eduaccess.repository.CursoRepository;
import com.upao.eduaccess.repository.EstudianteCursoRepository;
import com.upao.eduaccess.mapper.ComentarioMapper;
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

