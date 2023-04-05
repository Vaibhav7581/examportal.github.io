import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
qId;
qTitle;
question = {
  quiz:{

  },
  content:'',
  option1:'',
  option2:'',
  option3:'',
  option4:'',
  answer:'',
};
public Editor = ClassicEditor;

  constructor(
    private _route:ActivatedRoute,
    private _question:QuestionsService
    ) { }

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qId;
    this.qTitle= this._route.snapshot.params.title;
    this.question.quiz['qId'] = this.qId;
  }
  //form submit

  addQuestions()
  {
   if(this.question.content.trim()=='' || this.question.content==null)
   {
    return;
   }
   if(this.question.option1.trim()=='' || this.question.option1==null)
   {
    return;
   }
   if(this.question.option2.trim()=='' || this.question.option2==null)
   {
    return;
   }
   if(this.question.option3.trim()=='' || this.question.option3==null)
   {
    return;
   }
   if(this.question.option4.trim()=='' || this.question.option4==null)
   {
    return;
   }

   //form submit
   this._question.addQuestions(this.question).subscribe(

    //sucess
    (data:any)=>{
      this.question=data;
      Swal.fire("Success !!",'Question added Successfully','success');
      this.question = {
        quiz:{
      
        },
        content:'',
        option1:'',
        option2:'',
        option3:'',
        option4:'',
        answer:'',
      }
    },
    (error:any)=>{
      Swal.fire("Error !!",'Error while loading question','error')
    }
   );
  }

}
