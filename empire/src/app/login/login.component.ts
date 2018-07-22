import { Component, OnInit,  AfterViewInit, Output, EventEmitter } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { SignupComponent } from '../signup/signup.component';
import { UserService } from '../services/user.service';
import { IUser } from '../db_models/user';
import { ProfileService } from '../services/profile.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit, AfterViewInit {

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

  // login() {
  //   this._userService.login();
  // }
  login() {
    this._userService.getUser(this.username, this.password).subscribe(data => console.log(data));
    console.log('login');
    console.log(this.username);
    console.log(this._profileService.getCurrentUser());
    // this._profileService.setCurrentUser(this._profileService.getCurrentUser());
    // // this._userService.login();
    // this.close();
  }

}
