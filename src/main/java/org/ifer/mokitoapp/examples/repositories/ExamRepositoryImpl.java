package org.ifer.mokitoapp.examples.repositories;

import org.ifer.mokitoapp.examples.models.Exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Esta clase implementa la interfaz ExamRepository.
 * Sobrescribe el method findAll() para devolver una lista fija de objetos Exam que representan tres exámenes:
 * Matemáticas, Ciencias e Historia.
 * Propósito: Simula un repositorio de datos, como si los estuviera recuperando de una base de datos o API,
 * aunque aquí los datos están codificados directamente en la clase.
 */

public class ExamRepositoryImpl implements ExamRepository {

    @Override
    public List<Exam> findAll() {
        return Arrays.asList(new Exam(5L, "Math"),
                new Exam(6L, "Science"),
                new Exam(7L, "History"));
    }

}
