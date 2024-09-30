package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByMaterialId(Long materialId); // obtener las notas asociadas a un material
}
