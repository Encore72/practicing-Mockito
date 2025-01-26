package org.ifer.mokitoapp.examples.repositories;

import java.util.List;

public interface QuestionRepository {
    List<String> findQuestionsByExamId(Long id);
    void saveQuestions(List<String> questions);

}
