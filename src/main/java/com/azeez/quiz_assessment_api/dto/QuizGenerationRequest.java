package com.azeez.quiz_assessment_api.dto;

import com.azeez.quiz_assessment_api.entity.GradeLevel;
import com.azeez.quiz_assessment_api.entity.QuestionType;
import com.azeez.quiz_assessment_api.entity.Subject;
import lombok.Data;

import java.util.Map;

@Data
public class QuizGenerationRequest {
    private Subject subject;
    private GradeLevel gradeLevel;
    private Map<QuestionType, Integer> questionCounts;
}
