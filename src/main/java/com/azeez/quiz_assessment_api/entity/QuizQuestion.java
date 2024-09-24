package com.azeez.quiz_assessment_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "quiz_questions")
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String questionText;

    @ElementCollection
    @CollectionTable(name = "question_question_correct_answers",
            joinColumns = @JoinColumn(name = "quiz_question_id"))
    @Column(name = "correct_answer")
    private List<String> correctAnswers;

    @Column(name = "correct_answer_explanation")
    private String correctAnswerExplanation; // To provide hints for correct answer(optional)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType; // Add the questionType field

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;

    @OneToMany(mappedBy = "quizQuestion")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}

