package com.quiz.service;

import com.quiz.entities.QuizEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quiz.repositories.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public QuizEntity createQuiz(QuizEntity quiz) {
        try {
            logger.info("Creating new quiz: {}", quiz);
            return quizRepository.save(quiz);
        } catch (Exception e) {
            logger.error("Error while creating quiz", e);
            throw new RuntimeException("Failed to create quiz");
        }
    }

    @Override
    public List<QuizEntity> getAllQuizzes() {
        logger.info("Fetching all quizzes");
        return quizRepository.findAll();
    }

    @Override
    public Optional<QuizEntity> getQuizById(Long id) {
        logger.info("Fetching quiz by ID: {}", id);
        return quizRepository.findById(id);
    }

    @Override
    public QuizEntity updateQuiz(Long id, QuizEntity quiz) {
        try {
            Optional<QuizEntity> existingQuiz = quizRepository.findById(id);
            if (existingQuiz.isPresent()) {
                QuizEntity updatedQuiz = existingQuiz.get();
                updatedQuiz.setTitle(quiz.getTitle());
                logger.info("Updating quiz with ID {}: {}", id, updatedQuiz);
                return quizRepository.save(updatedQuiz);
            } else {
                logger.warn("Quiz with ID {} not found", id);
                throw new RuntimeException("Quiz not found");
            }
        } catch (Exception e) {
            logger.error("Error updating quiz with ID {}", id, e);
            throw new RuntimeException("Failed to update quiz");
        }
    }

    @Override
    public void deleteQuiz(Long id) {
        try {
            if (quizRepository.existsById(id)) {
                quizRepository.deleteById(id);
                logger.info("Deleted quiz with ID {}", id);
            } else {
                logger.warn("Quiz with ID {} not found", id);
                throw new RuntimeException("Quiz not found");
            }
        } catch (Exception e) {
            logger.error("Error deleting quiz with ID {}", id, e);
            throw new RuntimeException("Failed to delete quiz");
        }
    }
}
