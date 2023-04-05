package com.examportal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;



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

import com.examportal.model.Questions;
import com.examportal.model.Quiz;
import com.examportal.service.QuestionService;
import com.examportal.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")

public class QuestionsController {
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;

	//add question
    @PostMapping("/")
    public ResponseEntity<Questions> add(@RequestBody Questions question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<Questions> update(@RequestBody Questions question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get all question of any quid
    @GetMapping("/quiz/{questionid}")
 
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("questionid") Long questionid) {
//        Quiz quiz = new Quiz();
//        quiz.setqId(qid);
//        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = this.quizService.getQuizById(questionid);
        Set<Questions> questions = quiz.getQuestions();
        List<Questions> list = new ArrayList<Questions>(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberofquestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberofquestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/quiz/all/{questionid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("questionid") Long questionid) {
        Quiz quiz = new Quiz();
        quiz.setqId(questionid);
        Set<Questions> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);

//        return ResponseEntity.ok(list);


    }


    //get single question
    @GetMapping("/{questionid}")
    public Questions get(@PathVariable("questionid") Long questionid) {
        return this.questionService.getQuestion(questionid);
    }

    //delete question
    @DeleteMapping("/{questionid}")
    public void delete(@PathVariable("questionid") Long questionid) {
        this.questionService.deleteQuestion(questionid);
    }


    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Questions> questions) {
        System.out.println(questions);
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;
        for (Questions q : questions) {
            //single questions
            Questions question = this.questionService.get(q.getQuestionid());
            if (question.getAnswer().equals(q.getGivenAnswer())) {
                //correct
                correctAnswers++;

                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxmarks()) / questions.size();
                //       this.questions[0].quiz.maxMarks / this.questions.length;
                marksGot += marksSingle;

            }

            if (q.getGivenAnswer() != null) {
                attempted++;
            }

        }
        ;

        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);

    }


}
