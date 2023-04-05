package com.examportal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.examportal.dao.QuestionRepository;
import com.examportal.model.Questions;
import com.examportal.model.Quiz;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	SessionFactory factory;

	@Override
    public Questions addQuestion(Questions questions) {
        return this.questionRepository.save(questions);
    }

    @Override
    public Questions updateQuestion(Questions question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Questions> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Questions getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long questionid) {
        /*
         * Questions question = new Questions();
        question.setQuestionid(questionid);
         */
        this.questionRepository.deleteById(questionid);
    }

    @Override
    public Questions get(Long questionid) {
       return this.questionRepository.getOne(questionid);
    }
	

}
