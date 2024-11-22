package com.ucentral.Escuelabasket.EscuelaBasket.repositorios;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.representante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositorioRepresentante extends JpaRepository<representante, Long> {

    Optional<representante>findByUsuario(String usuario);

}
