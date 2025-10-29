package com.Trabajo._Ces2.Models;

import java.util.Stack;
import java.util.function.Function;

public class Estudiante {
    private final String nombre;
    private final Categorias categoria;
    private final Facultades facultad;
    private final Stack<Integer> librosPrestados = new Stack<>();

    public Estudiante(String nombre, Categorias categoria, Facultades facultad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.facultad = facultad;
    }

    public String obtenerNombre() { return nombre; }
    public Categorias obtenerCategoria() { return categoria; }
    public Facultades obtenerFacultad() { return facultad; }
    public Stack<Integer> obtenerLibros() { return librosPrestados; }

    public void agregarLibro(int librosSemana) {
        librosPrestados.push(librosSemana);
    }

    public static Integer sumarLibros(Estudiante p) {
        return p.librosPrestados.stream().mapToInt(Integer::intValue).sum();
    }

    public int verTotalLibros(Function<Estudiante, Integer> funcion) {
        Integer total = funcion.apply(this);
        System.out.println("Total Libros del Estudiante " + nombre + " = " + total);
        return total != null ? total : 0;
    }
}