import { Component, OnInit,  AfterViewInit, Output, EventEmitter } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { SignupComponent } from '../signup/signup.component';
import { UserService } from '../services/user.service';
import { IUser } from '../db_models/user';
import { ProfileService } from '../services/profile.service';
import { LostPassComponent } from '../lost-pass/lost-pass.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit, AfterViewInit {

  // create variable to hold username and password
  username: string;
  password: string;
  constructor(private _modalService: ModalService, private _userService: UserService, private _profileService: ProfileService) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
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

  // login() {
  //   this._userService.login();
  // }

  // when click login this function will call getUser method in user service
  login() {
    console.log('login');
    console.log(this.username);
    this._userService.getUser(this.username, this.password).subscribe(data => console.log(data));
    // this._profileService.setCurrentUser(this._profileService.getCurrentUser());
    // // this._userService.login();
    this.close();
  }

}
