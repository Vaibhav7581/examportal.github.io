import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {
  cId;
  quizzes=<any>[];
  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService
  ) { }

  ngOnInit(): void {
   this._route.params.subscribe((params)=>{
    this.cId = this._route.snapshot.params.cId;
    if (this.cId == 0) {
      //console.log("load all th quiz")
      this._quiz.getActiveQuizzes().subscribe(

        //success
        (data: any) => {
          this.quizzes = data;
          console.log(this.quizzes)

        },
        (error: any) => {
          console.log(error);
          Swal.fire("Error !!", 'Error while loading allQuizzes', 'error')
        }
      )
    }
    else {
      console.log("Load Specific Quiz");
      //this.quizzes = [];
      this._quiz.getQuizzesOfActiveCategory(this.cId).subscribe(

    //success
    (data:any)=>{
      this.quizzes = data;
    },
    (error:any)=>{
      Swal.fire("Error !!", 'Error while loading quiz data', 'error')
    }
      )
    }
   });
    
  }

}
