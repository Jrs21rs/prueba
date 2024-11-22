package com.ucentral.Escuelabasket.EscuelaBasket.repositorios;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioAlumno extends JpaRepository<Alumno, Long> {
    // Método personalizado para filtrar alumnos por nombre
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
}

