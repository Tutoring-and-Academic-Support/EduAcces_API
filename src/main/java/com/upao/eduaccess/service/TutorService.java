package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    public Tutor sesionTutor(Tutor tutor) {
        Tutor tutorExistente = tutorRepository.findTutorByEmailAndContraseña(tutor.getEmail(), tutor.getContraseña());
        if (tutorExistente != null) {
            return tutorExistente;
        } else {
            throw new RuntimeException("Tutor no encontrado");
        }
    }

    public Tutor findById(Integer id) {
        return tutorRepository.findById(id).orElse(null);
    }

    public Tutor update(Integer id, Tutor updateTutor) {
        Tutor tutorActual = tutorRepository.findById(id).orElse(null);
        tutorActual.setNombre(updateTutor.getNombre());

        return tutorRepository.save(tutorActual);
    }
}
