package com.examportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.Category;
import com.examportal.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	public List<Quiz> findByCategory(Category category);

	public List<Quiz> findByActive(Boolean b);

	public List<Quiz> findByCategoryAndActive(Category category, Boolean b);

}
