package com.ucentral.Escuelabasket.EscuelaBasket.config;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.administrador;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RepositorioAdministrador administradorRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Override
    public void run(String... args) throws Exception {
        if (administradorRepositorio.findById(1L).isEmpty()) {
            administrador admin = new administrador();
            admin.setSerial(1L);
            admin.setContacto("5551234");
            admin.setContrasena(passwordEncoder.encode("password123"));
            admin.setNombre("Admin Prueba");
            admin.setUsuario("admin_prueba");
            administradorRepositorio.save(admin);
        }
    }
}
