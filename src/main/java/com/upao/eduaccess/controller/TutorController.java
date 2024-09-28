package com.upao.eduaccess.controller;

import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loginT")
public class TutorController {
    @Autowired
    TutorService tutorService;

    @PostMapping("/loginT")
    public ResponseEntity<String> login(@RequestBody Tutor tutor) {
        Tutor tutorExistente = tutorService.sesionTutor(tutor);
        return ResponseEntity.ok("Inicio de sesion exitoso");
    }

    @GetMapping("/{id}")
    public Tutor getTutorById(@PathVariable int id ) {
        return tutorService.findById(id);
    }

}
