package com.upao.eduaccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upao.eduaccess.domain.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByEstudianteId(Long estudianteId);

}
