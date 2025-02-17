package com.quiz.service;

import com.quiz.entities.QuizEntity;
import java.util.List;
import java.util.Optional;

public interface QuizService {
    QuizEntity createQuiz(QuizEntity quiz);
    List<QuizEntity> getAllQuizzes();
    Optional<QuizEntity> getQuizById(Long id);
    QuizEntity updateQuiz(Long id, QuizEntity quiz);
    void deleteQuiz(Long id);
}
