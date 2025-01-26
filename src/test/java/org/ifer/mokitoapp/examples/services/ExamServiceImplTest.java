package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;
import org.ifer.mokitoapp.examples.repositories.ExamRepository;
import org.ifer.mokitoapp.examples.repositories.ExamRepositoryOther;
import org.ifer.mokitoapp.examples.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class ExamServiceImplTest {

    // al igual que hice en JUnit5 creo qun BeforeEach para inicializar el repository y el service una
    // sola vez antes de cada test y así no tener que repetir el codigo en cada test

    ExamRepository examRepository;
    ExamService service;
    QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        examRepository = mock(ExamRepository.class); //esto crea una implementación al vuelo simulada de ExamRepository
        questionRepository = mock(QuestionRepository.class);
        service = new ExamServiceImpl(examRepository, questionRepository);

    }

    @Test
    void findExamByName() {

     // ExamRepository repository = new ExamRepositoryImpl();  --!esto seria lo que se haria sin mokito

        when(examRepository.findAll()).thenReturn(Data.EXAMS);//method when de mockito, que dice que cuando se llame al method findAll() de repository, se devuelva la lista data

        Optional<Exam> exam = service.findExamByName("Math");

        assertTrue(exam.isPresent());
        assertEquals(5L, exam.orElseThrow().getId());
        assertEquals("Math", exam.orElseThrow().getName());
    }


    /* Para entender lo que hace Mockito, creo este ejemplo que similar al de arriba crea una implementación
    al vuelo de ExamRepositoryOther, y si bien ExamRepositoryOther tiene su propia clase en repositories y
    su propio method findAll() que simula un delay de 5s, como estamos haciendo un mock con Mockito, no se va
    a ejecutar ese metodo de delay de 5s, se va a ejecutar la implementacion al vuelo que estamos definiendo
    en el test, que es la que devuelve la lista de Exam con Math, Science e History
    */

    @Test
    void findExamByNameOther() {

        // ExamRepository repository = new ExamRepositoryOther();  --!esto seria lo que se haria sin mokito

        ExamRepository examRepository = mock(ExamRepositoryOther.class); //esto crea una implementación al vuelo simulada de ExamRepositoryOther

        ExamService service = new ExamServiceImpl(examRepository, questionRepository);
        List<Exam> data = Arrays.asList(
                new Exam(5L, "Math"),
                new Exam(6L, "Science"),
                new Exam(7L, "History"));
        when(examRepository.findAll()).thenReturn(data);//method when de mockito, que dice que cuando se llame al method findAll() de repository, se devuelva la lista data

        Optional<Exam> exam = service.findExamByName("Math");

        assertTrue(exam.isPresent());
        assertEquals(5L, exam.orElseThrow().getId());
        assertEquals("Math", exam.orElseThrow().getName());
    }


    // Importante: solo se puede hacer mock de methods publicos o default, no de methods privados o static


    @Test
    void findExamByNameEmptyList() {
        List<Exam> data = Collections.emptyList();

        when(examRepository.findAll()).thenReturn(data);
        Optional<Exam> exam = service.findExamByName("Math");

        assertFalse(exam.isPresent());
    }


    @Test
    void testQuestionsExam() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS); // simulacro de la base de datos de exámenes
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS); // simulacro de la base de datos de preguntas y usamos anyLong para que devuelva la lista de preguntas sin importar el id del examen
        Exam exam = service.findExamByNameWithQuestions("Math");
        assertEquals(5, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("arithmetic"));
    }

    /*
    ===== VERIFY =====
    El method verify en Mockito se utiliza para verificar que se haya producido una interacción específica con un objeto mockeado.
    Es decir, te permite comprobar si un method de un mock fue invocado, cuántas veces fue invocado y con qué argumentos. Esto es
    útil en pruebas unitarias para asegurarte de que el código bajo prueba está interactuando correctamente con sus dependencias.
     */


    @Test
    public void testVerify() {
        // Crear un mock de una lista
        List<String> mockedList = Mockito.mock(List.class);

        // Interactuar con el mock
        mockedList.add("elemento1");
        mockedList.add("elemento2");

        // Verificar que los métodos fueron llamados
        Mockito.verify(mockedList).add("elemento1");
        Mockito.verify(mockedList).add("elemento2");

        // Verificar que no hubo más interacciones
        Mockito.verifyNoMoreInteractions(mockedList);
    }

    @Test
    void testQuestionsExamVerify() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS); // simulacro de la base de datos de exámenes
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS); // simulacro de la base de datos de preguntas y usamos anyLong para que devuelva la lista de preguntas sin importar el id del examen
        Exam exam = service.findExamByNameWithQuestions("Math");
        assertEquals(5, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("arithmetic"));
        verify(examRepository).findAll();
        verify(questionRepository).findQuestionsByExamId(anyLong());
    }

    @Test
    void testQuestionsExamVerifyEmptyList() {
        when(examRepository.findAll()).thenReturn(Collections.emptyList());
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS);
        Exam exam = service.findExamByNameWithQuestions("Math2");
        assertNull(exam); // si la lista es nula no se puede hacer el verify porque no se invocan los methods
        verify(examRepository).findAll();
        verify(questionRepository).findQuestionsByExamId(anyLong());
    }

    

}