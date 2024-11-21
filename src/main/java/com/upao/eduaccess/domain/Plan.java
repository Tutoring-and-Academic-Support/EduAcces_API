package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan", nullable = false)
    private Integer idPlan;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

}
