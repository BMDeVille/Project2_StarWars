import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { ModalService } from '../services/modal.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-lost-pass',
  templateUrl: './lost-pass.component.html',
  styleUrls: ['./lost-pass.component.css']
})
export class LostPassComponent implements OnInit {
  email: string;

  constructor(private _modalService: ModalService, private _userService: UserService) { }

  ngOnInit() {
  }

  initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(LoginComponent, inputs, {});
  }

  submitForgottenPass() {
    console.log(this.email);
    this._userService.getUserByEmail(this.email);
  }

  public close() {
    this._modalService.destroy();
  }

}
