import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, AfterViewInit {

  // create an object that recevied any type of input
  reg: any = {};
  constructor(private _modalService: ModalService, private _userService: UserService) { }

  ngOnInit() {}

  ngAfterViewInit() {
  }

  public close() {
    this._modalService.destroy();
  }

  initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(LoginComponent, inputs, {});
  }

  // when click register button
  register() {
    // call user service and send register detail
    this._userService.regUser(this.reg).subscribe(data => console.log(data));
    console.log(this.reg);
  }
}
