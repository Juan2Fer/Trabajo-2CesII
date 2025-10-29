package com.Trabajo._Ces2.Models;

import java.util.ArrayDeque;
import java.util.Deque;

public class Bibliotecario {
    private final String nombre;
    private final Categorias categoria;
    private final Facultades facultad;
    private final Deque<Estudiante> estudiantes = new ArrayDeque<>();
    private final int librosCatalogados;

    public Bibliotecario(String nombre, Categorias categoria, Facultades facultad, int librosCatalogados) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.facultad = facultad;
        this.librosCatalogados = librosCatalogados;
    }

    public String obtenerNombre() { return nombre; }
    public Categorias obtenerCategoria() { return categoria; }
    public Facultades obtenerFacultad() { return facultad; }
    public Deque<Estudiante> obtenerEstudiantes() { return estudiantes; }
    public int obtenerLibrosCatalogados() { return librosCatalogados; }

    public void agregarEstudiante(Estudiante estudiante) { estudiantes.add(estudiante); }
}