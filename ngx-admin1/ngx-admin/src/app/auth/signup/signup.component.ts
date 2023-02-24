import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'ngx-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss','./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUp : FormGroup;
  submitted = false;
  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    

    this.signUp = new FormGroup({
      fullName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.minLength(3), Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
      repeatPassword: new FormControl('', [Validators.required, Validators.minLength(4)]),
      
    });

  }

  get fs() {
    return this.signUp.controls;
  }

  onSubmit(){
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }



}
