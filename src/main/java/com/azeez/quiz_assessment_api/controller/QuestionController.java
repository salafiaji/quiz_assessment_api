package com.azeez.quiz_assessment_api.controller;

import com.azeez.quiz_assessment_api.entity.QuizQuestion;
import com.azeez.quiz_assessment_api.service.QuestionService;
import com.azeez.quiz_assessment_api.service.QuizAttemptService;
import com.azeez.quiz_assessment_api.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuizService quizService;
    private final QuestionService questionService;
    private final QuizAttemptService quizAttemptService;

    @PostMapping
    public ResponseEntity<QuizQuestion> createQuestion(@RequestBody QuizQuestion question) {
        QuizQuestion createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuizQuestion> updateQuestion(@PathVariable Long questionId, @RequestBody QuizQuestion updatedQuestion) {
        QuizQuestion updated = questionService.updateQuestion(questionId, updatedQuestion);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
