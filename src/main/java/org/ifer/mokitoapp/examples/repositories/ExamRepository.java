package org.ifer.mokitoapp.examples.repositories;

import org.ifer.mokitoapp.examples.models.Exam;

import java.util.List;

public interface ExamRepository {
    Exam save(Exam exam);
    List<Exam> findAll();

}
