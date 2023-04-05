import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {

  qId;
  quiz;

  constructor(
private  _route:ActivatedRoute,
private _quiz:QuizService,
private _router:Router

  ) { }

  ngOnInit(): void {

    this.qId = this._route.snapshot.params.qId;
    this._quiz.getSingleQuiz(this.qId).subscribe(

  //suucess
  (data:any)=>{
   // console.log(data)
   this.quiz= data;
  },
  (error:any)=>{
    Swal.fire("Error !!","Error while loading the Quiz Data",'error')
  }
    )
  }

  //start quiz 
  
  startQuiz()
  {
    Swal.fire({
      icon:'info',
      title: 'Do you want to start the quiz?',
      showCancelButton: true,
      confirmButtonText: 'Start',

    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this._router.navigate(['/start/'+this.qId]);
       // Swal.fire('Saved!', '', 'success')
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }
}
