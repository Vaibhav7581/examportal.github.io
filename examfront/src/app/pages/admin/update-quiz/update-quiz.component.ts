import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {

  qId = undefined;
  quiz;
  categories;
  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService,
    private _category: CategoryService,
    private _router:Router

  ) { }

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qId;
    //alert(this.qId)
    this._quiz.getSingleQuiz(this.qId).subscribe(
      (data: any) => {
        //success
        this.quiz = data;
        console.log(this.quiz)
      },
      (error: any) => {
        Swal.fire("Error !", 'Error while loading quiz', 'error')
        console.log(error)
      }
    );

    //load the category

    this._category.categories().subscribe(
      (data: any) => {
        //success
        this.categories = data;
      },
      (error: any) => {
        Swal.fire("Error!!", 'error while loading the categories', 'error')
      }
    )
  }

  //update quiz 
  updateData() {
    this._quiz.updateQuiz(this.quiz).subscribe(
      (data: any) => {
        //success
        Swal.fire("Success !!", 'Quiz updated Successfully !!', 'success').then(
          (e)=>{
            this._router.navigate(['/admin/quizzes']);
          }
        )
      },
      (error:any)=>{
        Swal.fire("Error !!", 'Error while updating quiz !!', 'error')
      }
    )
  }

}
