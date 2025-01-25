package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;
import org.ifer.mokitoapp.examples.repositories.ExamRepository;

import java.util.Optional;


/**
 * Implementa la interfaz ExamService y define la lógica para buscar un examen por su nombre.
 * Utiliza un repositorio (ExamRepository) para obtener la lista de exámenes.
 * Esto aplica el principio de inyección de dependencias: el repositorio se proporciona al servicio mediante el constructor.
 * En el method findExamByName(String name):
 * Se usa un stream para filtrar la lista de exámenes basada en el nombre proporcionado.
 * Si encuentra un examen con el nombre dado, devuelve ese examen; de lo contrario, devuelve null.
 */

public class ExamServiceImpl implements ExamService{

    private ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam findExamByName(String name) {
        Optional<Exam> examOptional = examRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
        Exam exam = null;
        if (examOptional.isPresent()) {
            exam = examOptional.orElseThrow();
        }
        return exam;
    }
}
