package com.upao.eduaccess.service;

import com.upao.eduaccess.domain.Tutor;
import org.springframework.stereotype.Service;

public interface TutorService {
    public Tutor sesionTutor(Tutor tutor);
    public Tutor findById(Integer id);
    public Tutor update(Integer id, Tutor updateTutor);
}
