package com.quiz.controller;

import com.quiz.entities.QuizEntity;
import com.quiz.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    // Create Quiz
    @PostMapping
    public ResponseEntity<QuizEntity> createQuiz(@RequestBody QuizEntity quiz) {
        try {
            logger.info("Received request to create quiz");
            return ResponseEntity.ok(quizService.createQuiz(quiz));
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get All Quizzes
    @GetMapping
    public ResponseEntity<List<QuizEntity>> getAllQuizzes() {
        try {
            return ResponseEntity.ok(quizService.getAllQuizzes());
        } catch (Exception e) {
            logger.error("Error fetching quizzes", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get Quiz by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuizEntity> getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Quiz with ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // Update Quiz
    @PutMapping("/{id}")
    public ResponseEntity<QuizEntity> updateQuiz(@PathVariable Long id, @RequestBody QuizEntity quiz) {
        try {
            return ResponseEntity.ok(quizService.updateQuiz(id, quiz));
        } catch (Exception e) {
            logger.error("Error updating quiz with ID {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete Quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        try {
            quizService.deleteQuiz(id);
            return ResponseEntity.ok("Quiz deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting quiz with ID {}", id, e);
            return ResponseEntity.internalServerError().body("Failed to delete quiz");
        }
    }
}
