import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseurl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  constructor(
    private _http: HttpClient,
  ) { }

  //get questions of quiz

  getQuestionsOfQuiz(qId) {
    return this._http.get(`${baseurl}/question/quiz/all/${qId}`)
  }

  //get questions of quiz

  getQuestionsOfQuizForTest(qId) {
    return this._http.get(`${baseurl}/question/quiz/${qId}`)
  }
  //add questions
  addQuestions(question) {
     return this._http.post(`${baseurl}/question/`, question);
  }

  //delete question
  deleteQuestions(questionid)
  {
return this._http.delete(`${baseurl}/question/${questionid}`)
  }

  //eval quiz

  public evalQuiz(questions)
  {
    return this._http.post(`${baseurl}/question/eval-quiz`,questions)

  }
}
