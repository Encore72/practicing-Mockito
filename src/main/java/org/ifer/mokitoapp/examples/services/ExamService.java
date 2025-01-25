package org.ifer.mokitoapp.examples.services;

import org.ifer.mokitoapp.examples.models.Exam;

public interface ExamService {
    Exam findExamByName(String name);

}
