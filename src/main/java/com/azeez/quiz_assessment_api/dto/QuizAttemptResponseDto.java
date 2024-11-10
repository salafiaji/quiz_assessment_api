// QuizAttemptResponseDto.java (for returning attempt details)
package com.azeez.quiz_assessment_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptResponseDto {
    private Long attemptId;
    private Long quizId;
    private LocalDateTime attemptDate;
    private int score;
    private List<AnswerResultDto> answerResults;
}
