package com.ucentral.Escuelabasket.EscuelaBasket.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Alumno")
@Table(name = "Alumn_bk")
public class Alumno implements Serializable {

    @Id
    @Column(name = "ALM_Documento", nullable = false)
    private long AlumnoID;

    @Column(name = "ALM_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "ALM_NACIMIENTO", nullable = false)
    private Date Fecha_nac;

    @Column(name = "ENTR_ALTURA", unique = true, nullable = false)
    private String altura;

    @Column(name = "ENTR_HISTORIALMED", unique = true, nullable = false)
    private String Historial_med;

    @Column(name = "ENTR_PESO", nullable = false)
    private double peso;

    @Column(name = "ENTR_POSICION", nullable = false)
    private String posición_juego;

    /* Relación uno a muchos con Mensualidad
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<mensualidad> mensualidades;*/
}
