package com.examportal.dao;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.Questions;
import com.examportal.model.Quiz;

public interface QuestionRepository extends JpaRepository<Questions, Long>{

	Set<Questions> findByQuiz(Quiz quiz);

}
