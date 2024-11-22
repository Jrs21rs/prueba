package com.ucentral.Escuelabasket.EscuelaBasket.controladores;

import com.ucentral.Escuelabasket.EscuelaBasket.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auten")
public class ControladorAutenticador {

    @Autowired
    private ServicioUsuarios usuarioServicio;

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String contrasena) {
        return usuarioServicio.buscarPorUsuario(usuario)
                .filter(u -> u.getPassword().equals(contrasena))  // Comparación directa de contraseñas
                .map(u -> "Login exitoso, Rol: " + u.getAuthorities())
                .orElse("Usuario o contraseña incorrectos");
    }
}
