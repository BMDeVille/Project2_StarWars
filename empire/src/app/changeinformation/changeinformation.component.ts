import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { UserinformationComponent } from '../userinformation/userinformation.component';
import { UserService } from '../services/user.service';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';

@Component({
  selector: 'app-changeinformation',
  templateUrl: './changeinformation.component.html',
  styleUrls: ['./changeinformation.component.css']
})
export class ChangeinformationComponent implements OnInit {
  changeUser: IUser;
  constructor(private _modalService: ModalService, private _userService: ProfileService) {
  this.changeUser = _userService.getCurrentUser();
  }


  ngOnInit() {

  }

  public close() {
    this._modalService.destroy();
  }

  initAboutModal() {
    const inputs = {
      isMobile: false,
    };
    this._modalService.reInit(UserinformationComponent, inputs, {});
  }

}
