import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { UserinformationComponent } from '../userinformation/userinformation.component';
import { UserService } from '../services/user.service';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';
import { Router } from '@angular/router';
import { ProfileComponent } from '../profile/profile.component';
@Component({
  selector: 'app-changeinformation',
  templateUrl: './changeinformation.component.html',
  styleUrls: ['./changeinformation.component.css']
})
export class ChangeinformationComponent implements OnInit {
  changeUser: IUser;
  up: any = {};
  constructor(private _modalService: ModalService, private _profileService: ProfileService, private _userService: UserService,
     private _router: Router) {

  // this.changeUser = _profileService.getCurrentUser();
  this.changeUser = JSON.parse(localStorage.getItem('currentUser'));
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
    // console.log('inside info change');
    // console.log(this.up);
    this._userService.updateUser(this.up);
    setTimeout(() => {
      this.initAboutModal();
    }, 2500);

  }

  get username(): string {
    return this.changeUser.username;
  }
  get firstName(): string {
    return this.changeUser.fname;
  }
  get lastName(): string {
    return this.changeUser.lname;
  }
  get about(): string {
    return this.changeUser.about;
  }
  set username(username: string) {
    this.changeUser.username = username;
  }
  set firstName(fname: string) {
    this.changeUser.fname = fname;
  }
  set lastName(lname: string) {
    this.changeUser.lname = lname;
  }
  set about(about: string) {
    this.changeUser.about = about;
  }

}
