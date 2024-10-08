package com.azeez.quiz_assessment_api.service;

import com.azeez.quiz_assessment_api.entity.*;
import com.azeez.quiz_assessment_api.repository.QuizAttemptRepository;
import com.azeez.quiz_assessment_api.repository.QuizQuestionRepository;
import com.azeez.quiz_assessment_api.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizRepository quizRepository;


    public QuizAttempt submitQuiz(Long quizId, User user, List<QuizAttemptAnswer> answers) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + quizId));

        // Calculate score
        int score = 0;
        for (QuizAttemptAnswer answer : answers) {
            QuizQuestion question = quizQuestionRepository.findById(answer.getQuizQuestion().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + answer.getQuizQuestion().getId()));
            if (answer.getUserAnswer().equals(question.getCorrectAnswers().get(0))) {
                score++;
                answer.setCorrect(true);
            }
        }

        // Create and save QuizAttempt
        QuizAttempt quizAttempt = QuizAttempt.builder()
                .user(user)
                .quiz(quiz)
                .attemptDate(LocalDateTime.now())
                .score(score)
                .answers(answers)
                .build();

        return quizAttemptRepository.save(quizAttempt);
    }

    public List<QuizAttempt> getQuizHistory(User user) {
        return quizAttemptRepository.findAllByUser(user);
    }

    // Add other methods as needed (e.g., findQuizAttemptById, etc.)
}
