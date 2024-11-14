package com.upao.eduaccess.repository;

import com.upao.eduaccess.enums.TipoRole;
import com.upao.eduaccess.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(exported = false)
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(TipoRole name);
}
