package com.samta.assignment.repositories;

import com.samta.assignment.entities.Category;
import com.samta.assignment.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
