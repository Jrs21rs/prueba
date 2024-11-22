package com.ucentral.Escuelabasket.EscuelaBasket.servicios;

import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioAdministrador;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioEntrenador;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioRepresentante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioUsuarios implements UserDetailsService {

    @Autowired
    private RepositorioAdministrador administradorRepositorio;

    @Autowired
    private RepositorioEntrenador entrenadorRepositorio;

    @Autowired
    private RepositorioRepresentante representanteRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarPorUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    public Optional<UserDetails> buscarPorUsuario(String usuario) {
        // Intentar buscar en cada repositorio y devolver el resultado si se encuentra un usuario.
        return administradorRepositorio.findByUsuario(usuario)
                .map(admin -> (UserDetails) admin)
                .or(() -> entrenadorRepositorio.findByUsuario(usuario).map(entrenador -> (UserDetails) entrenador))
                .or(() -> representanteRepositorio.findByUsuario(usuario).map(representante -> (UserDetails) representante));
    }

}
