package com.ucentral.Escuelabasket.EscuelaBasket.repositorios;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositorioEntrenador extends JpaRepository<Entrenador, Long> {
        Optional<Entrenador> findByUsuario(String usuario);

}
