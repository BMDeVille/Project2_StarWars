import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { ChangeinformationComponent } from '../changeinformation/changeinformation.component';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';

@Component({
  selector: 'app-userinformation',
  templateUrl: './userinformation.component.html',
  styleUrls: ['./userinformation.component.css']
})
export class UserinformationComponent implements OnInit {
  viewUser: IUser;
  constructor( private _modal: ModalService, private userService: ProfileService) {
     this.viewUser = userService.getCurrentUser();
   }

  ngOnInit() {
  }

  close() {
    this._modal.destroy();
  }

  initChangeModal() {
    const inputs = {
      isMobile: false,
    };
    this._modal.reInit(ChangeinformationComponent, inputs, {});
  }

}
