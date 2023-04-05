package com.examportal.service;



import java.util.Set;

import com.examportal.model.Questions;
import com.examportal.model.Quiz;

public interface QuestionService {

	 public Questions addQuestion(Questions question);

	    public Questions updateQuestion(Questions question);

	    public Set<Questions> getQuestions();

	    public Questions getQuestion(Long questionid);

	    public Set<Questions> getQuestionsOfQuiz(Quiz quiz);

	    public void deleteQuestion(Long questionid);

	    public Questions get(Long questionid);
	    
	    

	

}
