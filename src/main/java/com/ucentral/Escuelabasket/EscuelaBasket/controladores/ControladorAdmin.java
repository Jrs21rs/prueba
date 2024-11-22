package com.ucentral.Escuelabasket.EscuelaBasket.controladores;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Entrenador;
import com.ucentral.Escuelabasket.EscuelaBasket.repositorios.RepositorioEntrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ControladorAdmin {
    @Autowired
    private RepositorioEntrenador entrenadorRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/Menuadmin")
    public String mostrarMenuAdmin(Model model) {
        // Recuperar todos los entrenadores y pasarlos al modelo
        List<Entrenador> entrenadores = entrenadorRepositorio.findAll();
        model.addAttribute("entrenadores", entrenadores); // Asegura que estén disponibles en la vista
        return "Menuadmin"; // Cargar la vista principal
    }


    // Método para registrar el entrenador
    @PostMapping("/registrarEntrenador")
    public String registrarEntrenador(@ModelAttribute Entrenador entrenador, RedirectAttributes redirectAttributes) {
        try {
            // Codificar la contraseña antes de guardar
            entrenador.setContrasena(passwordEncoder.encode(entrenador.getContrasena()));

            // Guardar el entrenador en la base de datos
            entrenadorRepositorio.save(entrenador);

            redirectAttributes.addFlashAttribute("mensaje", "Entrenador registrado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el entrenador.");
        }
        return "redirect:/Menuadmin"; // Redirigir a la vista principal
    }


    // Listar todos los entrenadores
    @GetMapping("/listar")
    public String listarEntrenadores(Model model) {
        // Obtener la lista de entrenadores desde la base de datos
        List<Entrenador> entrenadores = entrenadorRepositorio.findAll();
        System.out.println(entrenadores);
        model.addAttribute("entrenadores", entrenadores);
        return "Menuadmin"; // Archivo Thymeleaf para listar entrenadores

    }

    // Eliminar un entrenador por su cédula
    @GetMapping("/eliminar/{cedula}")
    public String eliminarEntrenador(@PathVariable Long cedula, RedirectAttributes redirectAttributes) {
        try {
            // Eliminar entrenador por ID
            entrenadorRepositorio.deleteById(cedula);

            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("mensaje", "Entrenador eliminado correctamente.");
        } catch (Exception e) {
            // Mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el entrenador.");
        }
        return "redirect:/Menuadmin"; // Redirigir a la lista de entrenadores
    }

    // Mostrar el formulario de edición de un entrenador
    @GetMapping("/editar/{cedula}")
    public String Editarentren(@PathVariable Long cedula, Model model) {
        Entrenador entrenador = entrenadorRepositorio.findById(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Entrenador no encontrado con cédula: " + cedula));
        model.addAttribute("entrenador", entrenador); // Pasar entrenador al modelo
        model.addAttribute("entrenadores", entrenadorRepositorio.findAll()); // Cargar listado completo
        return "Menuadmin";
    }


    // Guardar los cambios del entrenador editado
    @PostMapping("/editar/{cedula}")
    public String guardarCambiosEntrenador(
            @PathVariable Long cedula,
            @ModelAttribute Entrenador entrenador,
            RedirectAttributes redirectAttributes
    ) {
        try {
            // Buscar el entrenador existente
            Entrenador entrenadorExistente = entrenadorRepositorio.findById(cedula)
                    .orElseThrow(() -> new IllegalArgumentException("Entrenador no encontrado con cédula: " + cedula));

            // Actualizar los campos del entrenador
            entrenadorExistente.setNombre(entrenador.getNombre());
            entrenadorExistente.setContacto(entrenador.getContacto());
            entrenadorExistente.setUsuario(entrenador.getUsuario());
            if (!entrenador.getContrasena().isEmpty()) {
                entrenadorExistente.setContrasena(passwordEncoder.encode(entrenador.getContrasena()));
            }

            // Guardar los cambios en la base de datos
            entrenadorRepositorio.save(entrenadorExistente);

            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("mensaje", "Entrenador actualizado correctamente.");
        } catch (Exception e) {
            // Mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el entrenador.");
        }

        return "redirect:/Menuadmin"; // Redirigir a la lista de entrenadores
    }

}
