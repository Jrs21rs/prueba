package com.ucentral.Escuelabasket.EscuelaBasket.controladores;
import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Alumno;
import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Entrenador;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioAlumno;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioEntrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControladorEntrenador {
    @Autowired
    private RepositorioAlumno alumnoRepositorio;


    @GetMapping("/Menuentrenador")
    public String mostrarMenuentre() {
        return "Menuentrenador";
    }

    @PostMapping("/registrarAlumno")
    public String registrarAlumno(@ModelAttribute Alumno alumno, RedirectAttributes redirectAttributes) {
        try {

            // Guardar el alumno en la base de datos
            alumnoRepositorio.save(alumno);

            redirectAttributes.addFlashAttribute("mensaje", "Alumno registrado exitosamente.");
        } catch (Exception e) {
            // En caso de error, agregar mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al registrar el alumno.");
        }

        return "redirect:/Menuentrenador";
    }

}