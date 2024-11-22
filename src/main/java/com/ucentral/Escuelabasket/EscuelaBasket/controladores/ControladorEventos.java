package com.ucentral.Escuelabasket.EscuelaBasket.controladores;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Eventos;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioEventos;
import com.ucentral.Escuelabasket.EscuelaBasket.servicios.ServicioEventos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ControladorEventos{

    @Autowired
    private ServicioEventos eventosServicio;

    // Obtener todos los eventos
    @GetMapping("/")
    public List<Eventos> obtenerTodosEventos() {
        return eventosServicio.obtenerTodosEventos();
    }

    // Obtener un evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Eventos> obtenerEventoPorId(@PathVariable long id) {
        Optional<Eventos> evento = eventosServicio.obtenerEventoPorId(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear o actualizar un evento
    @PostMapping("/")
    public ResponseEntity<Eventos> crearEvento(@RequestBody Eventos evento) {
        Eventos nuevoEvento = eventosServicio.guardarEvento(evento);
        return new ResponseEntity<>(nuevoEvento, HttpStatus.CREATED);
    }

    // Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable long id) {
        eventosServicio.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar eventos por tipo
    @GetMapping("/tipo/{tipo}")
    public List<Eventos> buscarEventosPorTipo(@PathVariable String tipo) {
        return eventosServicio.buscarEventosPorTipo(tipo);
    }
}
