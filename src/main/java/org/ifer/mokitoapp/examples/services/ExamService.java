package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;

import java.util.Optional;

public interface ExamService {
    Optional<Exam> findExamByName(String name);

}
