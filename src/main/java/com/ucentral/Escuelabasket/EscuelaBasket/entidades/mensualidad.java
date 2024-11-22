package com.ucentral.Escuelabasket.EscuelaBasket.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Mensualidad")
@Table(name = "Mensualidad_bk")
public class mensualidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENSUALIDAD_ID")
    private Long id;

    @Column(name = "MONTO", nullable = false)
    private double monto;

    @Column(name = "FECHA_PAGO", nullable = false)
    private Date fechaPago;

    @Column(name = "ESTADO", nullable = false)
    private boolean estado;

    @Column(name = "MES_PAGO", nullable = false)
    private String mesPago;

    // Relación muchos a uno con Alumno
    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;
}
