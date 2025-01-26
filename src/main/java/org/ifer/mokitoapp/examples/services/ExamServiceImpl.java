package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;
import org.ifer.mokitoapp.examples.repositories.ExamRepository;
import org.ifer.mokitoapp.examples.repositories.QuestionRepository;

import java.util.List;
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
    private QuestionRepository questionRepository;

    public ExamServiceImpl(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<Exam> findExamByName(String name) {
      return examRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    @Override
    public Exam findExamByNameWithQuestions(String name) {
        // Paso 1: Busca un examen por nombre y devuelve un Optional<Exam>.
        // - findExamByName(name) busca un examen en la base de datos o repositorio.
        // - El resultado es un Optional<Exam>, que puede contener un examen o estar vacío.
        Optional<Exam> examOptional = findExamByName(name);

        // Paso 2: Inicializa una variable exam como null.
        // - Esta variable se usará para almacenar el examen encontrado.
        Exam exam = null;

        // Paso 3: Verifica si el Optional contiene un examen.
        // - isPresent() devuelve true si el Optional no está vacío.
        if (examOptional.isPresent()) {
            // Paso 4: Si el examen está presente, lo obtiene o lanza una excepción si no está presente.
            // - orElseThrow() obtiene el examen del Optional o lanza una excepción si está vacío.
            // - En este caso, como ya verificamos que el Optional no está vacío, simplemente devuelve el examen.
            exam = examOptional.orElseThrow();

            // Paso 5: Busca las preguntas asociadas al examen usando el ID del examen.
            // - questionRepository.findQuestionsByExamId(exam.getId()) busca las preguntas en el repositorio.
            // - El resultado es una lista de preguntas (List<String>).
            List<String> questions = questionRepository.findQuestionsByExamId(exam.getId());

            // Paso 6: Asigna las preguntas al examen.
            // - setQuestions(questions) establece la lista de preguntas en el objeto Exam.
            exam.setQuestions(questions);
        }

        // Paso 7: Retorna el examen (puede ser null si no se encontró).
        // - Si se encontró el examen, retorna el examen con las preguntas cargadas.
        // - Si no se encontró el examen, retorna null.
        return exam;
    }

}
