package com.azeez.quiz_assessment_api.repository;

import com.azeez.quiz_assessment_api.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // You can add custom query methods here if needed
    Optional<Quiz> findByName(String name);
    Optional<Quiz> findBySubjectAndGradeLevel(String subject, String gradeLevel);
    //Optional<Quiz> findById(Long id);
}
