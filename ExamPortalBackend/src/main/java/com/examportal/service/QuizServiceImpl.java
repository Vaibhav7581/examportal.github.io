package com.examportal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dao.QuizRepository;
import com.examportal.model.Category;
import com.examportal.model.Quiz;
@Service
@Transactional
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {

		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {

		return this.quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> getAllQuiz() {

		return this.quizRepository.findAll();
	}

	@Override
	public Quiz getQuizById(Long qId) {

		return this.quizRepository.findById(qId).get();
	}

	@Override
	public void deleteQuiz(Long qId) {
	
		this.quizRepository.deleteById(qId);

	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
	
		return this.quizRepository.findByCategory(category);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		
		return this.quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuiizesOfCategory(Category category) {
		
		return this.quizRepository.findByCategoryAndActive(category, true);
	}

}
