package com.azeez.quiz_assessment_api.repository;

import com.azeez.quiz_assessment_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
