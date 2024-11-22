package com.ucentral.Escuelabasket.EscuelaBasket.servicios;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Alumno;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServicio {
    @Autowired
    private RepositorioAlumno alumnoRepositorio;

    public List<Alumno> listarTodos() {
        return alumnoRepositorio.findAll();
    }

    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepositorio.findById(id);
    }

    public List<Alumno> filtrarPorNombre(String nombre) {
        return alumnoRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    public Alumno guardarAlumno(Alumno alumno) {
        return alumnoRepositorio.save(alumno);
    }

    public void eliminarAlumno(Long id) {
        alumnoRepositorio.deleteById(id);
    }
}