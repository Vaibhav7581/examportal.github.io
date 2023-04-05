import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseurl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private _http: HttpClient) { }
  //get all quiz
  public quizzes() {
    return this._http.get(`${baseurl}/quiz/`)
  }

  //add quiz
  public addQuiz(quiz) {
    return this._http.post(`${baseurl}/quiz/`, quiz)
  }

  //delete quiz
  public deleteQuiz(qId) {
    return this._http.delete(`${baseurl}/quiz/${qId}`)
  }

  //get single quiz
  getSingleQuiz(qId) {
    return this._http.get(`${baseurl}/quiz/${qId}`)
  }

  //update  quiz

  updateQuiz(quiz)
  {
    return this._http.put(`${baseurl}/quiz/`,quiz);
  }

  //get quizzes of category
  public getQuiizzesOfCategory(cId)
  {
    return this._http.get(`${baseurl}/quiz/category/${cId}`);
  }

  //get active quizzes

  public getActiveQuizzes()
  {

    return this._http.get(`${baseurl}/quiz/active`);
  }

  //get active quizzes of category

  public getQuizzesOfActiveCategory(cId)
  {
return this._http.get(`${baseurl}/quiz/category/active/${cId}`);
  }
}
