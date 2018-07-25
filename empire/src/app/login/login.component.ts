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
  user: IUser;

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
     this._userService.getUser(this.user).subscribe(data => this._profileService.setCurrentUser(data));
    console.log(this._profileService.getCurrentUser());
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


  // // when click login this function will call getUser method in user service
  // login() {
  //   console.log('login');
  //   console.log(this.username);
  //   // print out json to console, make sure receive from database
  //   this._userService.getUser(this.username, this.password).subscribe(data => console.log(data));
  //   // this._profileService.setCurrentUser(this._profileService.getCurrentUser());

  // }

}
