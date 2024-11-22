package com.ucentral.Escuelabasket.EscuelaBasket.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Eventos")
@Table(name = "Eventos_bk")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENTO")
    @SequenceGenerator(name = "SEQ_EVENTO", sequenceName = "SEQ_EVENTO", allocationSize = 1)
    @Column(name = "eve_codigo", nullable = false)
    private long codigo;

    @Column(name = "eve_tipo", nullable = false)
    private String tipo; //partido o entrenamiento

    @Column(name = "eve_fecha", nullable = false)
    private LocalDateTime fecha;  // Fecha y hora del evento

    @Column(name = "eve_lugar", nullable = false)
    private String lugar;

    @Column(name = "eve_categoria", nullable = true)
    private String categoria;

    @Column(name = "eve_ubicacion", nullable = true)
    private String ubicacion;
}
