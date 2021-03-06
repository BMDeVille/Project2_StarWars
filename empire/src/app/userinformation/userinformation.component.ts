import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { ChangeinformationComponent } from '../changeinformation/changeinformation.component';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userinformation',
  templateUrl: './userinformation.component.html',
  styleUrls: ['./userinformation.component.css']
})
export class UserinformationComponent implements OnInit {
  viewUser: IUser;
  activeUser: IUser;
  date = new Date();
  d = this.date.getFullYear();
  constructor( private _modal: ModalService, private userService: ProfileService, private router: Router) {
    // this.viewUser = userService.getCurrentUser();
   this.activeUser = JSON.parse(localStorage.getItem('currentUser'));
  //  this.viewUser = this.userService.getViewUser();
   if (JSON.parse(localStorage.getItem('viewUser')) === null) {
    this.viewUser = this.userService.getViewUser();
   } else {
    this.viewUser = JSON.parse(localStorage.getItem('viewUser'));
   }
  }

  ngOnInit() {
  }

  close() {
    this._modal.destroy();
    this.router.navigateByUrl('/profile');
  }

  initChangeModal() {
    const inputs = {
      isMobile: false,
    };
    this._modal.reInit(ChangeinformationComponent, inputs, {});
  }

}
