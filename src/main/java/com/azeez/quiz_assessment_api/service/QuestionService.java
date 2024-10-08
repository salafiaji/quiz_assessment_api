package com.azeez.quiz_assessment_api.service;

import com.azeez.quiz_assessment_api.entity.QuizQuestion;
import com.azeez.quiz_assessment_api.repository.QuizQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuizQuestionRepository quizQuestionRepository;

    public QuizQuestion createQuestion(QuizQuestion question) {
        return quizQuestionRepository.save(question);
    }

    public QuizQuestion updateQuestion(Long questionId, QuizQuestion updatedQuestion) {
        QuizQuestion existingQuestion = quizQuestionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + questionId));
        // Update properties of existingQuestion with updatedQuestion
        existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
        // ... update other properties as needed ...
        existingQuestion.setCorrectAnswers(updatedQuestion.getCorrectAnswers());
        existingQuestion.setCorrectAnswerExplanation(updatedQuestion.getCorrectAnswerExplanation());
        existingQuestion.setQuestionType(updatedQuestion.getQuestionType());
        existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
        existingQuestion.setAnswers(updatedQuestion.getAnswers());
        existingQuestion.setQuiz(updatedQuestion.getQuiz());

        // Save the updated question
        return quizQuestionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long questionId) {
        quizQuestionRepository.deleteById(questionId);
    }

    // Add other methods as needed (e.g., findQuestionById, findAllQuestions, etc.)
    public QuizQuestion findQuestionById(Long questionId) {
        return quizQuestionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + questionId));
    }

}
