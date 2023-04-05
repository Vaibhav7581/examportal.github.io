package com.examportal.service;

import java.util.List;

import com.examportal.model.Category;
import com.examportal.model.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public List<Quiz> getAllQuiz();

	public Quiz getQuizById(Long qId);

	public void deleteQuiz(Long qId);

	public List<Quiz> getQuizzesOfCategory(Category category);

	public  List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuiizesOfCategory(Category category);
	

}
