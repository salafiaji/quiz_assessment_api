package com.azeez.quiz_assessment_api.controller;

import com.azeez.quiz_assessment_api.dto.QuizGenerationRequest;
import com.azeez.quiz_assessment_api.entity.Quiz;
import com.azeez.quiz_assessment_api.service.QuestionService;
import com.azeez.quiz_assessment_api.service.QuizAttemptService;
import com.azeez.quiz_assessment_api.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final QuizAttemptService quizAttemptService;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        return ResponseEntity.ok(quizService.updateQuiz(id, updatedQuiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate")
    public ResponseEntity<Quiz> generateQuiz(@RequestBody QuizGenerationRequest request) {
        return ResponseEntity.ok(quizService.generateQuiz(request.getSubject(), request.getGradeLevel(), request.getQuestionCounts()));
    }


}