import { Component, OnInit,  AfterViewInit, Output, EventEmitter } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { SignupComponent } from '../signup/signup.component';
import { UserService } from '../services/user.service';
import { ProfileService } from '../services/profile.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit, AfterViewInit {

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

  login() {
    console.log(this._profileService.getCurrentUser());
    this._profileService.setCurrentUser(this._profileService.getCurrentUser());
    // this._userService.login();
    this.close();
  }

}
