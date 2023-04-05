package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.Category;
import com.examportal.model.Quiz;
import com.examportal.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// add quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	// update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}

	// get all quiz

	@GetMapping("/")
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		return ResponseEntity.ok(this.quizService.getAllQuiz());
	}

	// get single quiz

	@GetMapping("/{qid}")
	public Quiz getQuizById(@PathVariable Long qid) {
		return this.quizService.getQuizById(qid);
	}

	//delete the quiz
	
	@DeleteMapping("/{qid}")
	public void deleteQuiz(@PathVariable Long qid) {
		this.quizService.deleteQuiz(qid);
	}
	
	@GetMapping("/category/{cId}")
	public List<Quiz> getQuizzesOfCategories(@PathVariable Long cId )
	{
		Category category = new Category();
		category.setcId(cId);
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	// get active quizzes
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes()
	{
		return quizService.getActiveQuizzes();
	}
	@GetMapping("/category/active/cId")
	public List<Quiz> getActiveQuizzesByCategory(@PathVariable Long cId)
	{
		Category category = new Category();
		category.setcId(cId);
		return quizService.getActiveQuiizesOfCategory(category);
	}
}
