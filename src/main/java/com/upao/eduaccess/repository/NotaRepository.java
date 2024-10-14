package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(exported = false)
@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByMaterialId(Long materialId); // obtener las notas asociadas a un material
}
