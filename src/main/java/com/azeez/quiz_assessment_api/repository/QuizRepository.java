package com.azeez.quiz_assessment_api.repository;

import com.azeez.quiz_assessment_api.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // You can add custom query methods here if needed
    Optional<Quiz> findByName(String name);

}
