package com.ucentral.Escuelabasket.EscuelaBasket.controladores;

import com.ucentral.Escuelabasket.EscuelaBasket.entidades.Alumno;
import com.ucentral.Escuelabasket.EscuelaBasket.servicios.AlumnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumnos")
public class ControladorAlumno {
    @Autowired
    private AlumnoServicio alumnoServicio;

    @GetMapping
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoServicio.listarTodos());
        return "alumnos/lista"; // archivo lista.html en templates/alumnos
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "alumnos/formulario"; // archivo formulario.html en templates/alumnos
    }

    @PostMapping
    public String guardarAlumno(@ModelAttribute Alumno alumno) {
        alumnoServicio.guardarAlumno(alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Optional<Alumno> alumno = alumnoServicio.buscarPorId(id);
        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());
            return "alumnos/formulario";
        } else {
            return "redirect:/alumnos";
        }
    }

    @PostMapping("/editar/{id}")
    public String actualizarAlumno(@PathVariable Long id, @ModelAttribute Alumno alumno) {
        alumno.setAlumnoID(id);
        alumnoServicio.guardarAlumno(alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        alumnoServicio.eliminarAlumno(id);
        return "redirect:/alumnos";
    }

    @GetMapping("/buscar")
    public String filtrarAlumnos(@RequestParam String nombre, Model model) {
        List<Alumno> alumnos = alumnoServicio.filtrarPorNombre(nombre);
        model.addAttribute("alumnos", alumnos);
        return "alumnos/lista";
    }
}
