package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Curso;
import com.upao.eduaccess.dto.CursoDTO;
import com.upao.eduaccess.mapper.CursoMapper;
import com.upao.eduaccess.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    public List<CursoDTO> getAllCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> getCursosByCiclo(int ciclo) {
        return cursoRepository.findByCiclo(ciclo)
                .stream()
                .map(cursoMapper::toDto)
                .collect(Collectors.toList());
    }

    public CursoDTO getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        return cursoMapper.toDto(curso);
    }

    public CursoDTO saveCurso(CursoDTO cursoDTO) {
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Curso savedCurso = cursoRepository.save(curso);
        return cursoMapper.toDto(savedCurso);
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
