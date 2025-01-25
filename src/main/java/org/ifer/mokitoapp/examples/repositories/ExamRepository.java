package org.ifer.mokitoapp.examples.repositories;

import org.ifer.mokitoapp.examples.models.Exam;

import java.util.List;

public interface ExamRepository {
    List<Exam> findAll();

}
