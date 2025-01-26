package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;
import org.ifer.mokitoapp.examples.repositories.ExamRepository;
import org.ifer.mokitoapp.examples.repositories.ExamRepositoryOther;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class ExamServiceImplTest {

    @Test
    void findExamByName() {

     // ExamRepository repository = new ExamRepositoryImpl();  --!esto seria lo que se haria sin mokito

        ExamRepository repository = mock(ExamRepository.class); //esto crea una implementación al vuelo simulada de ExamRepository
        ExamService service = new ExamServiceImpl(repository);
        List<Exam> data = Arrays.asList(new Exam(5L, "Math"),
                new Exam(6L, "Science"),
                new Exam(7L, "History"));
        when(repository.findAll()).thenReturn(data);//method when de mockito, que dice que cuando se llame al method findAll() de repository, se devuelva la lista data

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

        ExamRepository repository = mock(ExamRepositoryOther.class); //esto crea una implementación al vuelo simulada de ExamRepositoryOther
        ExamService service = new ExamServiceImpl(repository);
        List<Exam> data = Arrays.asList(new Exam(5L, "Math"),
                new Exam(6L, "Science"),
                new Exam(7L, "History"));
        when(repository.findAll()).thenReturn(data);//method when de mockito, que dice que cuando se llame al method findAll() de repository, se devuelva la lista data

        Optional<Exam> exam = service.findExamByName("Math");

        assertTrue(exam.isPresent());
        assertEquals(5L, exam.orElseThrow().getId());
        assertEquals("Math", exam.orElseThrow().getName());
    }


    // Importante: solo se puede hacer mock de methods publicos o default, no de methods privados o static

}