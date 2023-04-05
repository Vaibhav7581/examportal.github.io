import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})

export class StartComponent implements OnInit {
  qId;
  questions: any = {};
  marksGot;
  correctAnswers;
  attempted;
  isSubmit = false;
  timer: any = undefined;
  constructor(

    private loactionstr: LocationStrategy,
    private _route: ActivatedRoute,
    private _question: QuestionsService,
  ) { }

  ngOnInit(): void {

    this.preventBackButton();
    this.qId = this._route.snapshot.params.qId;
    //console.log(this.qId)
    this.loadQuestions();
  }

  loadQuestions() {

    this._question.getQuestionsOfQuizForTest(this.qId).subscribe(
      //success
      (data: any) => {
        this.questions = data;
        // console.log(data)
        this.timer = this.questions.length * 2 * 60;

        // this.questions.forEach((q: any) => {
        //   q['givenAnswer'] = '';
        // })

        this.startTimer();
      },
      (error: any) => {
        Swal.fire("Error !!", 'Error while loading the question', 'error')
      }
    )

  }




  preventBackButton() {
    history.pushState(null, null, location.href);
    this.loactionstr.onPopState(() => {
      history.pushState(null, null, location.href);
    });
  }

  //submit quiz

  submitQuiz() {
    Swal.fire({
      icon: 'info',
      title: 'Do you want to submit the quiz?',
      showCancelButton: true,
      confirmButtonText: 'Submit',
      denyButtonText: 'Dont save',

    }).then((q) => {
      if (q.isConfirmed) {
        //calculation
        this.evalQuiz();
      }
    })
  }

  //start timer

  startTimer() {
    let t: any = window.setInterval(() => {
      //code
      if (this.timer <= 0) {
        this.submitQuiz();
        clearInterval(t);
      }
      else {
        this.timer--;
      }
    }, 1000
    )
  }

  //get formatted time
  getFormattedTime() {
    let minute = Math.floor(this.timer / 60);
    let second = this.timer - minute * 60;
    return `${minute} min : ${second} sec`;

  }
  // evalQuiz() {
  //   //calculation
  //   //call to sever  to check questions

  //   this.isSubmit = true;
  //   this.questions.forEach((q) => {
  //     if (q.givenAnswer == q.answer) {
  //       this.correctAnswers++;
  //       let marksSingle =
  //         this.questions[0].quiz.maxmarks / this.questions.length;
  //       this.marksGot += marksSingle;
  //     }
  //     if (q.givenAnswer.trim() != '') {
  //       this.attempted++;
  //     }
  //   });
  //   console.log('Correct Answers :' + this.correctAnswers);
  //   console.log('Marks Got ' + this.marksGot);
  //   console.log('attempted ' + this.attempted);
  //   console.log(this.questions);
  // }
  evalQuiz() {
    //calculation
    //call to sever  to check questions
    this._question.evalQuiz(this.questions).subscribe(
      (data: any) => {
        this.isSubmit = true;
        console.log(data);
        this.marksGot = parseFloat(Number(data.marksGot).toFixed(2));
        this.correctAnswers = data.correctAnswers;
        this.attempted = data.attempted;


      },
      (error: any) => {
        console.log(error);
      }
    )

  }

  //print page

  printPage() {
    window.print();
  }

}
// this._question.evalQuiz(this.questions).subscribe(
//   (data: any) => {
//     console.log(data);
//     this.marksGot = data.marksGot;
//     this.correctAnswers = data.correctAnswers;
//     this.attempted = data.attempted;
//     this.isSubmit = true;
//   },
//   (error) => {
//     console.log(error);
//   }
// );