package org.ifer.mokitoapp.examples.repositories;

import org.ifer.mokitoapp.examples.models.Exam;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamRepositoryOther implements ExamRepository {

    @Override
    public Exam save(Exam exam) {
        return null;
    }

    @Override
    public List<Exam> findAll() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
