package com.azeez.quiz_assessment_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResultDto {
    private Long questionId;
    private String userAnswer;
    private boolean isCorrect;
}
