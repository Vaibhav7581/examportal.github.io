import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {
  quizzes = [
    
  ];
  constructor(private _quiz: QuizService,) { }

  ngOnInit(): void {

    this._quiz.quizzes().subscribe(
      (data: any) => {
        this.quizzes = data;
        console.log(this.quizzes);
      },
      (error: any) => {
        console.log(error);
        Swal.fire("Error !!", 'Error in loading data', 'error')
      }
    )
  }
  //delete quiz
deleteQuiz(qId)
{
Swal.fire(
  {
    "icon":'info',
    "title":"are you sure?",
    confirmButtonText:'Delete',
    showCancelButton:true
  }
).then((result)=>{
  if(result.isConfirmed){
    //delete
    this._quiz.deleteQuiz(qId).subscribe(
      //success
      (data:any)=>{
    
        this.quizzes = this  .quizzes.filter((quiz)=> quiz.qId!=qId);
        Swal.fire("Success !!",'Quiz Deleted Successfully','success');
      },
    (error:any)=>{
      Swal.fire("Error !!",'Error while deleting the quiz','error');
    }
    )
  };
})
}

}
