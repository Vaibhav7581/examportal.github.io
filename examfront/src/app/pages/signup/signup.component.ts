import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userservice :UserService,private _snackBar: MatSnackBar) { }
  public user = {
    username:'',
    password: '',
    firstname: '',
    lastname: '',
    email: '',
    phonenumber: ''
  };

  ngOnInit(): void {}

  public formsubmit() {
    console.log(this.user);
    if (this.user.username == '' || this.user.username == null) {
      //alert("User is required ..!!!")
      this._snackBar.open('username is required !!','',{
        duration:3000,
      //  verticalPosition:'top',
        //horizontalPosition:'right'
      });
      return;
    }
    //validate


    
    // add user:userservice
     this.userservice.addUser(this.user).subscribe(
      (data)=>{
        //success
        console.log(data)
        //alert("success")
        Swal.fire('Successfully Done..!!','User is registered successfully..!!','success');
      }
      ,
      (error)=>{
        console.log(error);
        //alert("something went wrong");
        this._snackBar.open(error.error.text, '', {
          duration: 3000,
        });
      }

     )
  }
  
}
