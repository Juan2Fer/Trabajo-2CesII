package com.Trabajo._Ces2.Repositorys;

import com.Trabajo._Ces2.Models.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Stack;

@Repository
public class BibliotecaRepository {
    private final Stack<Estudiante> estudiantesStack = new Stack<>();
    private final Bibliotecario bibliotecarioPrincipal;

    public BibliotecaRepository() {
        Random random = new Random(42); // seed for reproducibility

        // Nombres de estudiantes para la facultad de INGENIERIA
        String[] nombresIngenieria = {
                "Carlos López", "Andrés García", "Miguel Torres",
                "David Silva", "Javier Ruiz", "Juan Martínez",
                "Pedro Hernández", "Luis Fernández"
        };

        // PUNTO 1: Crear 5 estudiantes de INGENIERIA MAESTRIA
        for (int i = 0; i < 5; i++) {
            String nombre = nombresIngenieria[i];
            Categorias categoria = Categorias.MAESTRIA;
            Facultades facultad = Facultades.INGENIERIA;
            Estudiante e = new Estudiante(nombre, categoria, facultad);

            // Agregar libros prestados de las últimas 5 semanas (entre 0 y 3 libros por semana)
            for (int semana = 0; semana < 5; semana++) {
                e.agregarLibro(random.nextInt(4)); // 0..3 libros por semana
            }
            estudiantesStack.push(e);
        }

        // Nombres para bibliotecarios
        String[] bibliotecarios = {"Roberto Martínez", "Carlos Bianchi", "José Pekerman", "Marcelo Bielsa"};
        String bibliotecarioNombre = bibliotecarios[random.nextInt(bibliotecarios.length)];

        // Crear bibliotecario principal de INGENIERIA MAESTRIA
        bibliotecarioPrincipal = new Bibliotecario(
                bibliotecarioNombre,
                Categorias.MAESTRIA,
                Facultades.INGENIERIA,
                8 + random.nextInt(5) // 8-12 libros catalogados
        );

        // Asignar estudiantes al bibliotecario
        Deque<Estudiante> dequeEstudiantes = new ArrayDeque<>(estudiantesStack);
        for (Estudiante e : dequeEstudiantes) {
            bibliotecarioPrincipal.agregarEstudiante(e);
        }
    }

    public Stack<Estudiante> obtenerEstudiantesStack() {
        return estudiantesStack;
    }

    public Bibliotecario obtenerBibliotecarioPrincipal() {
        return bibliotecarioPrincipal;
    }
}