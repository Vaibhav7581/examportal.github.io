import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit {
  qId;
  qTitle;
  questions = []
  constructor(
    private _route: ActivatedRoute,
    private _question: QuestionsService,
    private _snack: MatSnackBar

  ) { }

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qId;
    this.qTitle = this._route.snapshot.params.title;
    // console.log(this.qId);
    // console.log(this.qTitle);
    this._question.getQuestionsOfQuiz(this.qId).subscribe(

      (data: any) => {
        //success
        this.questions = data;
        console.log(this.questions)
      },
      (error: any) => {
        Swal.fire("Error !!", 'Error while loading the questions of quiz', 'error')
      }
    )

  }
  //deleete questions
  deleteQuestions(questionid) {
    Swal.fire(
      {
        icon: 'info',
        showCancelButton: true,
        confirmButtonText: 'Delete',
        title: 'Are you sure,want to delete this questions'
      }
    ).then((result) => {
      if (result.isConfirmed) {
        this._question.deleteQuestions(questionid).subscribe(

          //success
          (data: any) => {
            this._snack.open('Question Deleted Successfully', '', {
              duration: 2500,
            });
            //this.questions = this.questions.filter((e)=> e.questionid != questionid);
          },
          (error:any)=>{
            this._snack.open("Error in Deleting the Quesdtions",'',{
              duration:2500
            });
            console.log(error)
          }
        );
      }
    })
  }
}
