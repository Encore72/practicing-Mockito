package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;

import java.util.Arrays;
import java.util.List;

// creamos una clase Data con una lista de exámenes final y estática para poder usarla
// en los tests sin necesidad de instanciarla cada vez.

public class Data {
    public final static List<Exam> EXAMS = Arrays.asList(new Exam(5L, "Math"),
            new Exam(6L, "Science"),
            new Exam(7L, "History"));

    public final static List<String> QUESTIONS = Arrays.asList("arithmetic", "algebra", "geometry", "trigonometry", "calculus");

    public final static Exam EXAM = new Exam(8L, "Physics");
}
