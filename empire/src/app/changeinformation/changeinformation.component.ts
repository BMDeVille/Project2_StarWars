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
  up: any = {};

  constructor(private _modalService: ModalService, private _profileService: ProfileService, private _userService: UserService) {

  this.changeUser = _profileService.getCurrentUser();
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

  submitInfoChange() {
    this._userService.updateUser(this.up).subscribe(data => console.log(data));
    this.initAboutModal();

  }

}
