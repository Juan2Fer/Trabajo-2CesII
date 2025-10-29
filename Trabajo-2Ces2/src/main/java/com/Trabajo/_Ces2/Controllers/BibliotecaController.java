package com.Trabajo._Ces2.Controllers;

import com.Trabajo._Ces2.Models.*;
import com.Trabajo._Ces2.Repositorys.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository repositorio;

    @GetMapping("/sector1")
    public String sector1(Model modelo) {
        List<String> resumen = repositorio.obtenerEstudiantesStack().stream()
                .map(e -> e.obtenerNombre() + " - " + e.obtenerCategoria() + " - " + e.obtenerFacultad())
                .collect(Collectors.toList());
        Bibliotecario b = repositorio.obtenerBibliotecarioPrincipal();
        modelo.addAttribute("resumenEstudiantes", resumen);
        modelo.addAttribute("bibliotecarioNombre", b.obtenerNombre());
        modelo.addAttribute("bibliotecarioCategoria", b.obtenerCategoria());
        modelo.addAttribute("bibliotecarioFacultad", b.obtenerFacultad());
        modelo.addAttribute("librosCatalogados", b.obtenerLibrosCatalogados());
        return "sector1";
    }

    @GetMapping("/sector2")
    public String sector2(Model modelo) {
        List<String> desdeStack = repositorio.obtenerEstudiantesStack().stream()
                .map(e -> e.obtenerNombre() + " - " + e.obtenerCategoria() + " - " + e.obtenerFacultad())
                .collect(Collectors.toList());

        Deque<Estudiante> deque = repositorio.obtenerBibliotecarioPrincipal().obtenerEstudiantes();
        List<String> desdeDeque = new ArrayList<>();
        Iterator<Estudiante> it = deque.iterator();
        while (it.hasNext()) {
            Estudiante e = it.next();
            desdeDeque.add(e.obtenerNombre() + " - " + e.obtenerCategoria() + " - " + e.obtenerFacultad());
        }

        modelo.addAttribute("desdeStack", desdeStack);
        modelo.addAttribute("desdeDeque", desdeDeque);
        return "sector2";
    }

    @GetMapping("/sector3")
    public String sector3(Model modelo) {
        Deque<Estudiante> deque = repositorio.obtenerBibliotecarioPrincipal().obtenerEstudiantes();
        List<String> estudiantesMedicina = deque.stream()
                .filter(e -> e.obtenerFacultad() == Facultades.MEDICINA)
                .map(Estudiante::obtenerNombre)
                .collect(Collectors.toList());

        modelo.addAttribute("estudiantesMedicina", estudiantesMedicina);
        return "sector3";
    }

    @GetMapping("/sector4")
    public String sector4(Model modelo) {
        Function<Estudiante, Integer> funcionSumar = Estudiante::sumarLibros;
        List<String> totales = repositorio.obtenerEstudiantesStack().stream()
                .map(e -> e.obtenerNombre() + ": " + funcionSumar.apply(e))
                .collect(Collectors.toList());

        modelo.addAttribute("totales", totales);
        return "sector4";
    }

    @GetMapping("/sector5")
    public String sector5(Model modelo) {
        Estudiante ejemplo = repositorio.obtenerEstudiantesStack().peek();
        Function<Estudiante, Integer> funcion = Estudiante::sumarLibros;
        int total = ejemplo.verTotalLibros(funcion);

        modelo.addAttribute("ejemploNombre", ejemplo.obtenerNombre());
        modelo.addAttribute("ejemploTotal", total);
        modelo.addAttribute("ejemploFacultad", ejemplo.obtenerFacultad());
        return "sector5";
    }
}