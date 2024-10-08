package com.azeez.quiz_assessment_api.service;

import com.azeez.quiz_assessment_api.entity.*;
import com.azeez.quiz_assessment_api.repository.QuizQuestionRepository;
import com.azeez.quiz_assessment_api.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    public Quiz createQuiz(Quiz quiz) {
        // You might add validation logic here before saving
        return quizRepository.save(quiz);
    }

    public Quiz getQuiz(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + id));
    }

    public Quiz updateQuiz(Long id, Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + id));
        // Update properties of existingQuiz with updatedQuiz (be careful with questions)
        existingQuiz.setName(updatedQuiz.getName());
        existingQuiz.setDescription(updatedQuiz.getDescription());
        // ... update other properties as needed ...
        return quizRepository.save(existingQuiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }


    public Quiz generateQuiz(Subject subject, GradeLevel gradeLevel, Map<QuestionType, Integer> questionCounts) {
        // 1. Fetch questions matching criteria
        List<QuizQuestion> matchingQuestions = quizQuestionRepository.findAll()
                .stream()
                .filter(q -> q.getQuiz().getSubject() == subject &&
                        q.getQuiz().getGradeLevel() == gradeLevel)
                .collect(Collectors.toList());

        // 2. Handle insufficient questions
        if (matchingQuestions.isEmpty()) {
            throw new IllegalArgumentException("No questions found for the given subject and grade level.");
        }

        // 3. Group questions by type
        Map<QuestionType, List<QuizQuestion>> questionsByType = matchingQuestions.stream()
                .collect(Collectors.groupingBy(QuizQuestion::getQuestionType));

        // 4. Validate question counts
        for (QuestionType type : questionCounts.keySet()) {
            if (!questionsByType.containsKey(type) || questionsByType.get(type).size() < questionCounts.get(type)) {
                throw new IllegalArgumentException("Not enough questions available for type: " + type);
            }
        }

        // 5. Select questions for each type
        List<QuizQuestion> selectedQuestions = new ArrayList<>();
        for (QuestionType type : questionCounts.keySet()) {
            List<QuizQuestion> typeQuestions = questionsByType.get(type);
            Collections.shuffle(typeQuestions);
            selectedQuestions.addAll(typeQuestions.subList(0, questionCounts.get(type)));
        }

        // 6. Create the new Quiz object
        Quiz newQuiz = Quiz.builder()
                .name("Generated Quiz - " + subject + " - " + gradeLevel)
                .description("Auto-generated quiz")
                .subject(subject)
                .gradeLevel(gradeLevel)
                .questions(selectedQuestions)
                .createdBy(new User())
                .build();

        // 7. Save the quiz
        return quizRepository.save(newQuiz);
    }

}