import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {
categories=[];

quizData={
  title:'',
  description:'',
  maxmarks:'',
  numberofquestions:'',
  active:true,
  category:{
    cId:'',
  
  },
};
  constructor(
    private _cat:CategoryService,
    private _snack:MatSnackBar,
    private _quiz:QuizService

    ) { }

  ngOnInit(): void {

this._cat.categories().subscribe(
  (data:any)=>{
    //categories load
    this.categories = data;
    //console.log(this.categories);
  },
  (error:any)=>{
    console.log(error);
    Swal.fire("Error!1","error in loading data from server",'error')
  }
)

  }
  addQuiz()
  {
    //console.log(this.quizData);
    if(this.quizData.title.trim()=='' || this.quizData.title==null)
    {
       this._snack.open("Title required" ,'' ,{
        duration:3000,
       })
       return;
    }

  //validation
  //call the server
   this._quiz.addQuiz(this.quizData).subscribe(
//success call back
    (data:any)=>
{
  Swal.fire("Sucess !!" ,'Quiz added successfully','success')
  this.quizData={
    title:'',
    description:'',
    maxmarks:'',
    numberofquestions:'',
    active:true,
    category:{
      cId:'',
    
    },
  };
}  ,
(error:any)=>{
  Swal.fire("Error !!",'Error while adding quiz','error');
  console.log(error)
}


)
  }
}
