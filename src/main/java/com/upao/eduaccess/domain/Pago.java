package com.upao.eduaccess.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false)
    private Long idPago;

    @Column(name = "fecha_pago", nullable = false)
    private Date fechaPago;

    @Column(name = "monto_total", nullable = false)
    private double montoTotal;

    @OneToMany(mappedBy = "pago")
    private List<DetallePago> detallePagos;
}


