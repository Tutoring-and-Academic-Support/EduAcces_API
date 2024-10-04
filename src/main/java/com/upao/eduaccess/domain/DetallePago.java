package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_pago")
public class DetallePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle", nullable = false)
    private Long idDetalle;

    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "cantidad_estudiantes", nullable = false)
    private int cantidadEstudiantes;

    @Column(name = "precio_por_estudiante", nullable = false)
    private double precioPorEstudiante;

    @ManyToOne
    @JoinColumn(name = "id_pago", referencedColumnName = "id_pago", nullable = false)
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "id_tutor", referencedColumnName = "id_tutor", nullable = false)
    private Tutor tutor;

}
