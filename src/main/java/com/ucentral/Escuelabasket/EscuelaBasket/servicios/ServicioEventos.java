package com.ucentral.Escuelabasket.EscuelaBasket.servicios;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Eventos;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioEventos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEventos {

    @Autowired
    private RepositorioEventos eventosRepositorio;

    // Obtener todos los eventos
    public List<Eventos> obtenerTodosEventos() {
        return eventosRepositorio.findAll();
    }

    // Obtener un evento por ID
    public Optional<Eventos> obtenerEventoPorId(long id) {
        return eventosRepositorio.findById(id);
    }

    // Guardar o actualizar un evento
    public Eventos guardarEvento(Eventos evento) {
        return eventosRepositorio.save(evento);
    }

    // Eliminar un evento por ID
    public void eliminarEvento(long id) {
        eventosRepositorio.deleteById(id);
    }

    // Buscar eventos por tipo (opcional)
    public List<Eventos> buscarEventosPorTipo(String tipo) {
        return eventosRepositorio.findByTipo(tipo);
    }
}
