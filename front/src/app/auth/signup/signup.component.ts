import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SignupRequestPayload} from './singup-request.payload';
import {AuthService} from '../shared/auth.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload: SignupRequestPayload;
  signupForm: FormGroup;

  constructor(private authService: AuthService, private router: Router,
              private toastr: ToastrService) {
    this.signupRequestPayload = {
      patientname: '',
      adress: '',
      email: '',
      password: '',
      phone: 0,
      ps: '',
      cin: 0,
      assurance: 0,
      diplome: ''
    };
  }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      adress: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
      ps: new FormControl('', Validators.required),
      cin: new FormControl('', Validators.required),
      assurance: new FormControl('', Validators.required),
      diplome: new FormControl('', Validators.required)
    });
  }

  signUp() {
    this.signupRequestPayload.patientname = this.signupForm.get('username').value;
    this.signupRequestPayload.adress = this.signupForm.get('adress').value;
    this.signupRequestPayload.password = this.signupForm.get('password').value;
    this.signupRequestPayload.email = this.signupForm.get('email').value;
    this.signupRequestPayload.phone = this.signupForm.get('phone').value;
    this.signupRequestPayload.ps = this.signupForm.get('ps').value;
    this.signupRequestPayload.cin = this.signupForm.get('cin').value;
    this.signupRequestPayload.assurance = this.signupForm.get('assurance').value;
    this.signupRequestPayload.diplome = this.signupForm.get('diplome').value;

    this.authService.signup(this.signupRequestPayload).subscribe(() => {
        this.router.navigate(['/login'],
          { queryParams: { registered: 'true' } });
      }, () => {
        this.toastr.error('Registration Failed! Please try again');
      });
  }

}
