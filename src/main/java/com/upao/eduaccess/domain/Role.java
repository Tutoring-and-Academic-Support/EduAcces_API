package com.upao.eduaccess.domain;

import com.upao.eduaccess.enums.TipoRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private TipoRole name;  // Enum con los roles: TUTOR, ESTUDIANTE
}

