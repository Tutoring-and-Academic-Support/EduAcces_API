package com.upao.eduaccess.repository;

import com.upao.eduaccess.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
