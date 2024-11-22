package com.ucentral.Escuelabasket.EscuelaBasket.repositorios;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioAdministrador extends JpaRepository<administrador , Long> {

    Optional<administrador> findByUsuario(String usuario);

}
