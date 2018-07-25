import { Component, OnInit,  AfterViewInit, Output, EventEmitter } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { SignupComponent } from '../signup/signup.component';
import { UserService } from '../services/user.service';
import { IUser } from '../db_models/user';
import { ProfileService } from '../services/profile.service';
import { LostPassComponent } from '../lost-pass/lost-pass.component';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit, AfterViewInit {

  // create variable to hold username and password
  isValidFormSubmitted = null;
  user: any;
  loginForm: FormGroup;

  constructor(private _modalService: ModalService, private _userService: UserService,
     private _profileService: ProfileService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngAfterViewInit() {
  }

  onFormSubmit() {
    this.isValidFormSubmitted = false;
    if (this.loginForm.invalid) {
      return;
    }
    this.isValidFormSubmitted = true;
    console.log(this.loginForm.value);
    this.user = this.loginForm.value;
    this._userService.getUser(this.user);
    //  this._userService.getUser(this.user).subscribe(data => this._profileService.setViewUser(data));
     setTimeout(() => {
       this.successLogin();
     }, 400);
    }

  public successLogin() {
    if (this._profileService.getCurrentUser() === null || this._profileService.getCurrentUser()  === undefined) {
      console.log('no user found');
      document.getElementById('login-status').innerHTML = 'Login failed.';
      return;
    }
    this.close();
  }


  public close() {
    this._modalService.destroy();
  }

  initSignupModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(SignupComponent, inputs, {});
  }

  initLostPassModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(LostPassComponent, inputs, {});
  }


}
