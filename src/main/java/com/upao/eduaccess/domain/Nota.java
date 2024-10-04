package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "texto", columnDefinition = "TEXT", nullable = false)
    private String texto;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
    private Material material;
}


