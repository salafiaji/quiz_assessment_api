package com.azeez.quiz_assessment_api.repository;

import com.azeez.quiz_assessment_api.entity.QuizAttempt;
import com.azeez.quiz_assessment_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findAllByUser(User user);
}
