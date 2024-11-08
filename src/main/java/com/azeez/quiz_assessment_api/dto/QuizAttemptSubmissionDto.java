package com.azeez.quiz_assessment_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptSubmissionDto {
    private Long quizId;
    private List<QuizAnswerDto> answers;
}
